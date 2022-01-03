package tacos.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import tacos.entity.Customer;

@DataJpaTest
public class CustomerRepositoryTest {

	@Autowired
	private TestEntityManager testEntityManager;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Test
	void whenFindByFirstName_thenReturnCustomer() {
		//given
		Customer alex = new Customer();
		alex.setFirstName("alex");
		
		testEntityManager.persist(alex);
		testEntityManager.flush();
		
		//when
		Customer customer = this.customerRepository.findByFirstName("alex");
		
		//then
		assertThat(customer.getFirstName()).isEqualTo(alex.getFirstName());
		
	}
	
	@Test
	void whenFindByNameAndMail_thenReturnCustomer() {
		//given
		Customer alex = new Customer();
		alex.setFirstName("alex");
		alex.setLastName("ross");
		alex.setMail("alex.ross@gmail.com");
		
		testEntityManager.persist(alex);
		testEntityManager.flush();
		
		//when
		Customer customer = this.customerRepository.findByFirstNameAndLastNameAndMail("alex", "ross", "alex.ross@gmail.com");
		
		//then
		assertThat(customer.getMail()).isEqualTo(alex.getMail());
		
	}
	
	@Test
	void whenFindByFirstNameLike_thenReturnCustomer() {
		//given
		Customer Alex = new Customer();
		Alex.setFirstName("alex");
		Alex.setLastName("ross");
		Alex.setMail("alex.ross@gmail.com");
		
		Customer Joe = new Customer();
		Joe.setFirstName("joe");
		Joe.setLastName("smith");
		Joe.setMail("joe.smith@gmail.com");
		
		Customer Kobe = new Customer();
		Kobe.setFirstName("kobe");
		Kobe.setLastName("ross");
		Kobe.setMail("kobe.ross@gmail.com");
		
		testEntityManager.persist(Alex);
		testEntityManager.persist(Joe);
		testEntityManager.persist(Kobe);
		testEntityManager.flush();
		
		//when
		List<Customer> customerList = this.customerRepository.findByFirstNameContains("o");
		
		//then
		assertThat(customerList.size()).isEqualTo(2);
		
		customerList = this.customerRepository.findByFirstNameContaining("o");
		assertThat(customerList.size()).isEqualTo(2);
		
		customerList = this.customerRepository.findByFirstNameIsContaining("o");
		assertThat(customerList.size()).isEqualTo(2);
		
		customerList = this.customerRepository.findByLastNameLike("%os%");
		assertThat(customerList.size()).isEqualTo(2);
		
	}
}
