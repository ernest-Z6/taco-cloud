package tacos.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import tacos.dto.CustomerRequest;
import tacos.dto.CustomerResponse;
import tacos.dto.CustomerUpdateRequest;
import tacos.exception.ResourceNotFoundException;
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
	public List<CustomerResponse> getAllCustomer() {
		return this.customerService.getAll();
	}
	
	@PostMapping("/")
	public CustomerResponse createCustomer(@Valid @RequestBody CustomerRequest request) {
		return this.customerService.createCustomer(request);
	}
	
	@PutMapping("/")
	public CustomerResponse updateCustomer(@Valid @RequestBody CustomerUpdateRequest request) throws ResourceNotFoundException {
		return this.customerService.updateCustomer(request);
	}
	
}
