package tacos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tacos.dto.CustomerRequest;
import tacos.dto.CustomerResponse;
import tacos.dto.CustomerUpdateRequest;
import tacos.entity.Customer;
import tacos.exception.ResourceNotFoundException;
import tacos.mapper.CustomerMapper;
import tacos.repository.CustomerRepository;
import tacos.util.MessageConstants;

@Service
@AllArgsConstructor
@Slf4j
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
				.orElseThrow(() -> new ResourceNotFoundException(MessageConstants.CUSTOMER_NOT_FOUND));
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
	
	public ResponseEntity<String> deleteCustoemr(String id) throws ResourceNotFoundException {
		log.info("delete customer " + id);
		Customer customer = this.customerRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(MessageConstants.CUSTOMER_NOT_FOUND));
		this.customerRepository.delete(customer);
		return new ResponseEntity<>("Deleted customer " + id + ".", HttpStatus.OK);
	}
	
	public Page<CustomerResponse> getAllWithPagination(Integer pageNo, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Customer> customerPage = this.customerRepository.findAll(pageable);
		
		List<CustomerResponse> customerResponseList = new ArrayList<>();
		customerPage.stream().forEach(customer -> {
			customerResponseList.add(this.customerMapper.toCustomerResponse(customer));
		});
		
		return new PageImpl<>(customerResponseList, customerPage.getPageable(), customerPage.getTotalElements());
	}
	
	public List<CustomerResponse> getAllWithSorting() {
		Sort sort = Sort.by(Sort.Direction.ASC, "name");
		List<Customer> customerList = this.customerRepository.findAll(sort);

		List<CustomerResponse> customerResponseList = new ArrayList<>();
		customerList.stream().forEach(customer -> {
			customerResponseList.add(this.customerMapper.toCustomerResponse(customer));
		});
		
		return customerResponseList;
	}
}
