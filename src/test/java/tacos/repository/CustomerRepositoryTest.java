package tacos.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import tacos.entity.Customer;
import tacos.entity.Post;

@DataJpaTest
public class CustomerRepositoryTest {

	@Autowired
	private TestEntityManager testEntityManager;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@BeforeEach
	public void init() {
		Customer alex = Customer.builder()
				.firstName("alex")
				.lastName("ross")
				.mail("alex.ross@gmail.com")
				.post(Post.builder()
						.city("Delhi")
						.postalCode("0000")
						.build())
				.build();
		
		Customer john = Customer.builder()
				.firstName("john")
				.lastName("smith")
				.mail("john.smith@outlook.com")
				.post(Post.builder()
						.city("Delhi")
						.postalCode("0001")
						.build())
				.build();
		
		Customer ross = Customer.builder()
				.firstName("ross")
				.lastName("klass")
				.mail("ross.klass@db.com")
				.post(Post.builder()
						.city("Tokyo")
						.postalCode("881")
						.build())
				.build();
		
		testEntityManager.persist(alex);
		testEntityManager.persist(john);
		testEntityManager.persist(ross);
		
		testEntityManager.flush();
		
	}
	
	@AfterEach
	public void clean() {
		testEntityManager.clear();
	}
	
	@Test
	void testFindByOneProperty() {
		
		//when
		Customer customer = this.customerRepository.findByFirstName("alex");
		
		//then
		assertThat(customer.getFirstName()).isEqualTo("alex");
		
	}
	
	@Test
	void testFindBySeveralProperties() {
		
		//when
		Customer customer = this.customerRepository.findByFirstNameAndLastNameAndMail("alex", "ross", "alex.ross@gmail.com");
		
		//then
		assertThat(customer.getMail()).isEqualTo("alex.ross@gmail.com");
		
	}
	
	@Test
	void testFindWithDifferentSuffix() {
		
		//Contains
		List<Customer> customerList = this.customerRepository.findByFirstNameContains("o");
		assertThat(customerList.size()).isEqualTo(2);
		
		//Containing
		customerList = this.customerRepository.findByFirstNameContaining("o");
		assertThat(customerList.size()).isEqualTo(2);
		
		//IsContaining
		customerList = this.customerRepository.findByFirstNameIsContaining("o");
		assertThat(customerList.size()).isEqualTo(2);
		
		//Like
		customerList = this.customerRepository.findByLastNameLike("%ss%");
		assertThat(customerList.size()).isEqualTo(2);
		
		//StartsWith
		customerList =  this.customerRepository.findByFirstNameStartsWith("al");
		assertThat(customerList.size()).isEqualTo(1);
		
		//EndsWith
		customerList = this.customerRepository.findByLastNameEndsWith("ss");
		assertThat(customerList.size()).isEqualTo(2);
	}
	
	@Test
	void testFindByJPQL() {
		Customer customer = this.customerRepository.getByFirstNameAndLastName("alex", "ross");
		assertThat(customer.getFirstName()).isEqualTo("alex");
		
		customer = this.customerRepository.getByFirstNameAndLastName2("john", "smith");
		assertThat(customer.getFirstName()).isEqualTo("john");
		
		customer = this.customerRepository.getByFirstNameAndLastName3("john", "smith");
		assertThat(customer.getFirstName()).isEqualTo("john");
	}
	
	@Test
	void testLazyLoading() {
		
		//TODO How to test Lazy Fetch in Unit test?
		
	}
	
	@Test
	void testUpdateMail() {
		
		int num = this.customerRepository.updateMail("alex.ross@gmail66.com", "alex", "ross");
		Customer alex = this.customerRepository.getByFirstNameAndLastName("alex", "ross");

		System.out.println(alex.getMail());
		testEntityManager.refresh(alex);
		System.out.println(alex.getMail());
		
		assertThat(num).isEqualTo(1);
		
		num = this.customerRepository.updateMail("alex.ross@gmail.com", "John", "Smith");
		assertThat(num).isEqualTo(0);
	}
	
	@Test
	void testDeleteCustomer() {
		this.customerRepository.deleteByFirstName("alex");
		assertThat(this.customerRepository.findAll().size()).isEqualTo(2);
	}
}
