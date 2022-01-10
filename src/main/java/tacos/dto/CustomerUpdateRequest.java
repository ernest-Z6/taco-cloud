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
	
	@JsonProperty("customerFirstName")
	private String firstName;
	
	@JsonProperty("customerLastName")
	private String lastName;
	
	private String phone;
	
	private int age;
	
	private PostUpdateRequest post;
}
