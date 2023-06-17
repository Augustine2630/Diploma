package com.diploma.authorization;

import com.diploma.authorization.DTO.UserDTO;
import com.diploma.authorization.model.User;
import com.diploma.authorization.repository.UserRepository;
import com.diploma.authorization.service.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthorizationApplicationTests {

	@Autowired
	private UserRepository service;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	void registrationWorksThroughAllLayers() throws Exception {

		UserDTO userDTO = new UserDTO("12345", "1234", "John", "Snow");
		ObjectMapper objectMapper = new ObjectMapper();

		mockMvc.perform(post("/auth/registration", 42L)
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(userDTO)))
				.andExpect(status().isOk());

	}

	@Test
	public void userFound() {
		List<User> user = service.findAllByUsername("12345");
		assert !user.isEmpty();
	}
}
