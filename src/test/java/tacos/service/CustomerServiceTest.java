package tacos.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import tacos.dto.CustomerCreateRequest;
import tacos.dto.CustomerResponse;
import tacos.entity.Customer;
import tacos.entity.Post;
import tacos.repository.CustomerRepository;

@SpringBootTest
public class CustomerServiceTest {

	@Resource
	private CustomerService cut;

	@MockBean
	private CustomerRepository customerRepository;
	
	@Test
	public void when_create_customer_then_return_customer() {
		Customer customer = new Customer();
		customer.setFirstName("John");
		customer.setLastName("Smith");
		Post post = new Post();
		post.setPostalCode("210000");
		customer.setPost(post);
		
		when(this.customerRepository.save(any(Customer.class))).thenReturn(customer);
		
		CustomerCreateRequest request = new CustomerCreateRequest();
		CustomerResponse created = this.cut.createCustomer(request);
		
		assertThat(created.getPostalCode()).isSameAs(customer.getPost().getPostalCode());
	}
}
