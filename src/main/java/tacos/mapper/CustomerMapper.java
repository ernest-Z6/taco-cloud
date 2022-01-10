package tacos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import tacos.dto.CustomerCreateRequest;
import tacos.dto.CustomerResponse;
import tacos.dto.CustomerUpdateRequest;
import tacos.entity.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

	@Mapping(target = "name", source = ".", qualifiedByName = "toFullName")
	@Mapping(target = "postalCode", source = "post.postalCode")
	CustomerResponse toCustomerResponse(Customer customer);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "fullName", ignore = true)
	@Mapping(target = "post.id", ignore = true)
	Customer fromCustomerRequest(CustomerCreateRequest customerRequest);
	
	@Mapping(target = "fullName", ignore = true)
	@Mapping(target = "mail", ignore = true)
	@Mapping(target = "post.city", ignore = true)
	Customer fromCustomerRequest(CustomerUpdateRequest customerUpdateRequest);
	
	@Named("toFullName")
	default
    String translateToFullName(Customer customer) {
        return customer.getFirstName() + " " + customer.getLastName();
    }

}
