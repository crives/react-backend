package com.cognixia.jump.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceAlreadyExistsException;
import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Address;
import com.cognixia.jump.repository.AddressRepository;

import io.swagger.annotations.ApiOperation;

/**
 * The controller for Address.
 * @author Darreal Chambers and Lori White
 * @version v3 (08/29/2020)
 */
@RequestMapping("/api")
@RestController
public class AddressController {

	@Autowired
	AddressRepository service;
	
	/**
	 * Retrieves an address by their id.
	 * @author Darreal Chambers and Lori White
	 * @param id the address id to search for
	 * @return Address - the address that pertains to the id
	 * @throws ResourceNotFoundException is thrown when the address id does not match any existing entries with that specified address id in the database
	 */
	@GetMapping("/address/{id}")
	@ApiOperation( value = "", 
		notes = "Retrieves an address by their id.\n"
	   		+ "Usage: provide an id to look up an address in the database\n"
	   		+ "Author(s): Darreal Chambers and Lori White\n"
	   		+ "Execption(s): ResourceNotFoundException is thrown when the address id does not match any existing entries with that specified address id in the database",
	   	response = Address.class, produces = "application/json")
	public Address getAddressById(@PathVariable Long id) throws ResourceNotFoundException {
		Optional<Address> found = service.findById(id);
		if(found.isEmpty()) {
			throw new ResourceNotFoundException("Address with id=" + id + " not found");
		}
		return found.get();
	}
	/**
	 * Adds an address to the database.
	 * @author Lori White
	 * @param newAddress a new address to add
	 * @return ResponseEntity - a response of was created and the created address
	 * @throws ResourceAlreadyExistsException is thrown when the id or the street and  the zip does match an existing address in the database
	 */
	@PostMapping("/add/address")
	@ApiOperation( value = "", 
	notes = "Adds an address to the database.\n"
   		+ "Usage: provide a new address to add to the database\n"
   		+ "Author(s): Lori White\n"
   		+ "Execption(s): ResourceAlreadyExistsException is thrown when the id or the street and  the zip does match an existing address in the database",
   	response = ResponseEntity.class)
	public ResponseEntity<Address> addAddress(@RequestBody Address newAddress) throws ResourceAlreadyExistsException {
		if(service.existsById(newAddress.getId())) {
			throw new ResourceAlreadyExistsException("Address with id= " + newAddress.getId() + " already exists.");
		}
		if(service.existsByStreetAndZip(newAddress.getStreet(), newAddress.getZip())) {
			throw new ResourceAlreadyExistsException("Address with street= " + newAddress.getStreet() + " and zip= " + newAddress.getZip() + " already exists.");
		}
		Address created = service.insert(newAddress);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}
	/**
	 * Updates the street of an address.
	 * @author Lori White
	 * @param addressStreet a map that holds an address id of the address and the address's street to update
	 * @return ResponseEntity - a response of was accepted and the updated address
	 * @throws ResourceNotFoundException is thrown when the id does not match an existing address in the database
	 */
	@PatchMapping("/patch/address/street")
	@ApiOperation( value = "", 
	notes = "Updates the street of an address.\n"
   		+ "Usage: provide a map that holds an address id of the address and the address's street to update in the database\n"
   		+ "Author(s): Lori White\n"
   		+ "Execption(s): ResourceNotFoundException is thrown when the id does not match an existing address in the database",
   	response = ResponseEntity.class)
	public ResponseEntity<Address> patchAddressStreet(@RequestBody Map<String, String> addressStreet) throws ResourceNotFoundException {
		Long id = Long.parseLong(addressStreet.get("id"));
		String newStreet = addressStreet.get("street");
		Optional<Address> address = service.findById(id);
		if(address.isEmpty()) {
			throw new ResourceNotFoundException("Address with id= " + id + " was not found.");
		}
		Address updated = address.get();
		updated.setStreet(newStreet);
		service.save(updated);
		return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
	}
	/**
	 * Updates the city of an address.
	 * @author Lori White
	 * @param addressCity a map that holds an address id of the address and the address's city to update
	 * @return ResponseEntity - a response of was accepted and the updated address
	 * @throws ResourceNotFoundException is thrown when the id does not match an existing address in the database
	 */
	@PatchMapping("/patch/address/city")
	@ApiOperation( value = "", 
	notes = "Updates the city of an address.\n"
   		+ "Usage: provide a map that holds an address id of the address and the address's city to update in the database\n"
   		+ "Author(s): Lori White\n"
   		+ "Execption(s): ResourceNotFoundException is thrown when the id does not match an existing address in the database",
   	response = ResponseEntity.class)
	public ResponseEntity<Address> patchAddressCity(@RequestBody Map<String, String> addressCity) throws ResourceNotFoundException {
		Long id = Long.parseLong(addressCity.get("id"));
		String newCity = addressCity.get("city");
		Optional<Address> address = service.findById(id);
		if(address.isEmpty()) {
			throw new ResourceNotFoundException("Address with id= " + id + " was not found.");
		}
		Address updated = address.get();
		updated.setCity(newCity);
		service.save(updated);
		return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
	}
	/**
	 * Updates the state of an address.
	 * @author Lori White
	 * @param addressState a map that holds an address id of the address and the address's state to update
	 * @return ResponseEntity - a response of was accepted and the updated address
	 * @throws ResourceNotFoundException is thrown when the id does not match an existing address in the database
	 */
	@PatchMapping("/patch/address/state")
	@ApiOperation( value = "", 
	notes = "Updates the state of an address.\n"
   		+ "Usage: provide a map that holds an address id of the address and the address's state to update in the database\n"
   		+ "Author(s): Lori White\n"
   		+ "Execption(s): ResourceNotFoundException is thrown when the id does not match an existing address in the database",
   	response = ResponseEntity.class)
	public ResponseEntity<Address> patchAddressState(@RequestBody Map<String, String> addressState) throws ResourceNotFoundException {
		Long id = Long.parseLong(addressState.get("id"));
		String newState = addressState.get("state");
		Optional<Address> address = service.findById(id);
		if(address.isEmpty()) {
			throw new ResourceNotFoundException("Address with id= " + id + " was not found.");
		}
		Address updated = address.get();
		updated.setState(newState);
		service.save(updated);
		return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
	}
	/**
	 * Updates the zip of an address.
	 * @author Lori White
	 * @param addressZip a map that holds an address id of the address and the address's zip to update
	 * @return ResponseEntity - a response of was accepted and the updated address
	 * @throws ResourceNotFoundException is thrown when the id does not match an existing address in the database
	 */
	@PatchMapping("/patch/address/zip")
	@ApiOperation( value = "", 
	notes = "Updates the zip of an address.\n"
   		+ "Usage: provide a map that holds an address id of the address and the address's zip to update in the database\n"
   		+ "Author(s): Lori White\n"
   		+ "Execption(s): ResourceNotFoundException is thrown when the id does not match an existing address in the database",
   	response = ResponseEntity.class)
	public ResponseEntity<Address> patchAddressZip(@RequestBody Map<String, String> addressZip) throws ResourceNotFoundException {
		Long id = Long.parseLong(addressZip.get("id"));
		String newZip = addressZip.get("zip");
		Optional<Address> address = service.findById(id);
		if(address.isEmpty()) {
			throw new ResourceNotFoundException("Address with id= " + id + " was not found.");
		}
		Address updated = address.get();
		updated.setZip(newZip);
		service.save(updated);
		return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
	}
}
