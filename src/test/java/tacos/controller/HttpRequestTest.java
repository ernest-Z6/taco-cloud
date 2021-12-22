package tacos.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import tacos.dto.CustomerResponse;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

	@LocalServerPort
	private int port;
	
	/**
	 * https://www.baeldung.com/spring-boot-testresttemplate
	 * TestRestTemplate
	 */
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Test
	public void greetingShouldReturnDefaultMessage() {
		ResponseEntity<CustomerResponse> response = this.testRestTemplate
				.getForEntity("http://localhost:" + port + "/api/customer/", CustomerResponse.class);
		assertThat(response.getBody().getName(), equalTo("John"));
	}
}
