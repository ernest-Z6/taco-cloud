package tacos.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import tacos.dto.CustomerRequest;
import tacos.dto.CustomerResponse;
import tacos.entity.Customer;
import tacos.mapper.CustomerMapper;
import tacos.service.CustomerService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/customer")
public class CustomerController {
	
	CustomerService customerService;
	CustomerMapper customerMapper;

	@GetMapping("/")
	public CustomerResponse getCustomer() {
		return new CustomerResponse("John", "John.Ross@dbschenker.com");
	}
	
	@GetMapping("/all")
	public List<CustomerResponse> getAllCustomer() {
		List<Customer> customerList = customerService.getAll();
		List<CustomerResponse> customerResponseList = new ArrayList<>();
		
		customerList.stream().forEach(customer -> {
			customerResponseList.add(customerMapper.toCustomerResponse(customer));
		});
		
		return customerResponseList;
	}
	
	@PostMapping("/")
	public CustomerResponse createCustomer(@RequestBody CustomerRequest request) {
		return this.customerMapper.toCustomerResponse(this.customerService.createCustomer(request));
	}
	
}
