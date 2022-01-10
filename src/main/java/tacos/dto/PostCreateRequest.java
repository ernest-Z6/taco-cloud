package tacos.dto;


import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostCreateRequest {

	@NotBlank
	private String postalCode;
	
	private String city;
}
