package tacos.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerRequest {
	
	@JsonProperty("customerName")
	private String name;
	
	@JsonProperty("customerEmail")
	private String mail;

}
