package tacos.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerUpdateRequest {

	@NotNull
	private String id;
	
	@JsonProperty("customerName")
	private String name;
	
	@JsonProperty("customerEmail")
	private String mail;
}
