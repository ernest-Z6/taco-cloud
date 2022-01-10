package tacos.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostUpdateRequest {

	@NotNull
	private String id;
	
	@NotBlank
	private String postalCode;
	
}
