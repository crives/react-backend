package com.cognixia.jump.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
