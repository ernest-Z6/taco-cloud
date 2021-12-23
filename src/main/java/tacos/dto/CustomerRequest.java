package tacos.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerRequest {
	
	@JsonProperty("customerName")
	@NotBlank
	private String name;
	
	@JsonProperty("customerEmail")
	private String mail;

}
