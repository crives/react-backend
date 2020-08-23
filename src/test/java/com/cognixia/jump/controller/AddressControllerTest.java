package com.cognixia.jump.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.cognixia.jump.model.Address;
import com.cognixia.jump.repository.AddressRepository;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AddressController.class)
class AddressControllerTest {

	private final String STARTING_URI = "http://localhost:8080/api";
	
	@MockBean
	private AddressRepository repo;
	
	AddressController controller;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void testGetAddressById() throws Exception {
		String uri = STARTING_URI + "/address/{id}";
		long id = 1;
		
		Address address = new Address(id, "127 test street", "Asheboro", "NC", "27205");
		
		when( repo.findById(id) ).thenReturn(Optional.of(address));
		
		mockMvc.perform( get(uri, id) )
			.andExpect( status().isOk() )
			.andExpect( content().contentType( MediaType.APPLICATION_JSON ) )
			.andExpect( jsonPath("$.id").value(address.getId()) )
			.andExpect( jsonPath("$.street").value(address.getStreet()))
			.andExpect( jsonPath("$.city").value(address.getCity()))
			.andExpect( jsonPath("$.state").value(address.getState()))
			.andExpect( jsonPath("$.zip").value(address.getZip()));
		
		verify(repo, times(1)).findById(id);
		verifyNoMoreInteractions(repo);	
	}

}
