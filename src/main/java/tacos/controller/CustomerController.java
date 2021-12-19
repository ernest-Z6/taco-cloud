package tacos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tacos.response.CustomerResponse;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	@GetMapping("/")
	public CustomerResponse getCustomer() {
		return new CustomerResponse("John", "John.Ross@dbschenker.com");
	}
	
}
