package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.Address;
import com.cognixia.jump.repository.AddressRepository;

@RequestMapping("/api")
@RestController
public class AddressController {

	@Autowired
	AddressRepository service;
	
	@GetMapping("/addresses")
	public List<Address> getAllAddresses() {
		return service.findAll();
	}
	
	@GetMapping("/address/{id}")
	public Address getAddressById(@PathVariable String id) throws Exception {
		Optional<Address> found = service.findById(id);
		
		if(!found.isPresent()) {
			throw new Exception("Address with id=" + id + " not found");
		}
		
		return found.get();
	}
}
