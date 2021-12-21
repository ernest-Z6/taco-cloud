package tacos.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import tacos.entity.Customer;
import tacos.response.CustomerResponse;
import tacos.service.CustomerService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/customer")
public class CustomerController {
	
	CustomerService customerService;

	@GetMapping("/")
	public CustomerResponse getCustomer() {
		return new CustomerResponse("John", "John.Ross@dbschenker.com");
	}
	
	@GetMapping("/all")
	public List<Customer> getAllCustomer() {
		return customerService.getAll();
	}
	
}
