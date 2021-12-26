package tacos.repository;

import static org.assertj.core.api.Assertions.assertThat;

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
	void whenFindByName_thenReturnCustomer() {
		//given
		Customer alex = new Customer();
		alex.setName("alex");
		
		testEntityManager.persist(alex);
		testEntityManager.flush();
		
		//when
		Customer customer = this.customerRepository.findByName("alex");
		
		//then
		assertThat(customer.getName()).isEqualTo(alex.getName());
		
	}
	
	@Test
	void whenFindByNameAndMail_thenReturnCustomer() {
		//given
		Customer alex = new Customer();
		alex.setName("alex");
		alex.setMail("alex@gmail.com");
		
		testEntityManager.persist(alex);
		testEntityManager.flush();
		
		//when
		Customer customer = this.customerRepository.findByNameAndMail("alex", "alex@gmail.com");
		
		//then
		assertThat(customer.getName()).isEqualTo(alex.getName());
		
	}
	
	
}
