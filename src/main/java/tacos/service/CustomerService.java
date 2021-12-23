package tacos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import lombok.AllArgsConstructor;
import tacos.dto.CustomerRequest;
import tacos.dto.CustomerResponse;
import tacos.dto.CustomerUpdateRequest;
import tacos.entity.Customer;
import tacos.exception.ResourceNotFoundException;
import tacos.mapper.CustomerMapper;
import tacos.repository.CustomerRepository;

@Service
@AllArgsConstructor
public class CustomerService {

	CustomerRepository customerRepository;
	CustomerMapper customerMapper;
	
	public List<CustomerResponse> getAll() {
		List<Customer> customerList = this.customerRepository.findAll();
		List<CustomerResponse> customerResponseList = new ArrayList<>();
		
		customerList.stream().forEach(customer -> {
			customerResponseList.add(this.customerMapper.toCustomerResponse(customer));
		});
		
		return customerResponseList;
	}
	
	public CustomerResponse createCustomer(CustomerRequest request) {
		Customer customer = this.customerMapper.fromCustomerRequest(request);
		return this.customerMapper.toCustomerResponse(this.customerRepository.save(customer));
	}
	
	public CustomerResponse updateCustomer(CustomerUpdateRequest request) throws ResourceNotFoundException {
		Customer customer = this.customerRepository.findById(request.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found."));
		Boolean updateFlag = false;
		if (!ObjectUtils.isEmpty(request.getName())) {
			customer.setName(request.getName());
			updateFlag = true;
		}
		if (!ObjectUtils.isEmpty(request.getMail())) {
			customer.setMail(request.getMail());
			updateFlag = true;
		}
		if (updateFlag) {
			customer = this.customerRepository.save(customer);
		}
		return this.customerMapper.toCustomerResponse(customer);
	}
	
}
