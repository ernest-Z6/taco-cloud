package tacos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import tacos.entity.Customer;
import tacos.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	public List<Customer> getAll() {
		List<Customer> customerList = customerRepository.findAll();
		return customerList;
	}
	
}
