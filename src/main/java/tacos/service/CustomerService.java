package tacos.service;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import java.util.List;
import tacos.entity.Customer;
import tacos.repository.CustomerRepository;

@Service
@AllArgsConstructor
public class CustomerService {

	CustomerRepository customerRepository;
	
	public List<Customer> getAll() {
		List<Customer> customerList = customerRepository.findAll();
		return customerList;
	}
	
}
