package tacos.mapper;

import org.mapstruct.Mapper;

import tacos.dto.CustomerRequest;
import tacos.dto.CustomerResponse;
import tacos.dto.CustomerUpdateRequest;
import tacos.entity.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

	CustomerResponse toCustomerResponse(Customer customer);
	
	Customer fromCustomerRequest(CustomerRequest customerRequest);
	
	Customer fromCustomerRequest(CustomerUpdateRequest customerUpdateRequest);

}
