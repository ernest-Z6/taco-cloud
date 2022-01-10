package tacos.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerCreateRequest {
	
	@JsonProperty("customerFirstName")
	@NotBlank
	private String firstName;
	
	@JsonProperty("customerLastName")
	@NotBlank
	private String lastName;
	
	@JsonProperty("customerMail")
	private String mail;
	
	private String phone;
	
	private int age;
	
	private PostCreateRequest post;

}
