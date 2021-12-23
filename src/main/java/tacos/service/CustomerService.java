package tacos.service;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import java.util.List;

import tacos.dto.CustomerRequest;
import tacos.entity.Customer;
import tacos.mapper.CustomerMapper;
import tacos.repository.CustomerRepository;

@Service
@AllArgsConstructor
public class CustomerService {

	CustomerRepository customerRepository;
	CustomerMapper customerMapper;
	
	public List<Customer> getAll() {
		List<Customer> customerList = customerRepository.findAll();
		return customerList;
	}
	
	public Customer createCustomer(CustomerRequest request) {
		Customer customer = customerMapper.fromCustomerRequest(request);
		return customerRepository.save(customer);
	}
	
}
