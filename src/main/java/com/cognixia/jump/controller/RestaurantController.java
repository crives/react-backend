package com.cognixia.jump.controller;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceAlreadyExistsException;
import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Restaurant;
import com.cognixia.jump.repository.RestaurantRepository;

import io.swagger.annotations.ApiOperation;

/**
 * The controller for the Restaurants. 
 * @author Lori White
 * @version v3 (08/31/2020)
 */
@RequestMapping("/api")
@RestController
public class RestaurantController {
	@Autowired 
	RestaurantRepository service;
	@Autowired
	private MongoOperations mongoTemplate;
	
	/**
	 * Retrieves all the restaurants in the database and sorts them by name in ascending order.
	 * @author Lori White
	 * @return List - the restaurants in the database
	 */
	@GetMapping("/restaurants")
	@ApiOperation( value = "", 
	notes = "Retrieves all the restaurants in the database and sorts them by name in ascending order.\n"
   		+ "Usage: looks up a list of all restaurants in the database\n"
   		+ "Author(s): Lori White\n"
   		+ "Execption(s): None",
   	response = List.class, produces = "application/json")
	public List<Restaurant> getAllRestaurants() {
		return service.findAll(Sort.by(Sort.Direction.ASC, "name"));
	}
	/**
	 * Retrieves a restaurant pertaining to an id.
	 * @author Lori White
	 * @param id the restaurant id to search for
	 * @return Restaurant - the restaurant pertaining to the id
	 * @throws ResourceNotFoundException is thrown when the id does not match an existing restaurant in the database
	 */
	@GetMapping("/restaurant/{id}")
	@ApiOperation( value = "", 
	notes = "Retrieves a restaurant pertaining to an id.\n"
   		+ "Usage: provide an id to look up a restaurant in the database\n"
   		+ "Author(s): Lori White\n"
   		+ "Execption(s): ResourceNotFoundException is thrown when the id does not match an existing restaurant in the database",
   	response = Restaurant.class, produces = "application/json")
	public Restaurant getRestaurantById(@PathVariable Long id) throws ResourceNotFoundException {
		Optional<Restaurant> restaurant = service.findById(id);

		if(restaurant.isEmpty()) {
			throw new ResourceNotFoundException("The restaurant with id= " + id + " does not exist in the database.");
		}
		return restaurant.get();
	}
	/**
	 * Adds a restaurant to the database.
	 * @author Lori White
	 * @param newRestaurant a new restaurant to add
	 * @return ResponseEntity - a response of was created and the created restaurant
	 * @throws ResourceAlreadyExistsException is thrown when the id or the name and the address id does match an existing restaurant in the database
	 */
	@PostMapping("/add/restaurant")
	@ApiOperation( value = "", 
	notes = "Adds a restaurant to the database.\n"
   		+ "Usage: provide a new restaurant to add to the database\n"
   		+ "Author(s): Lori White\n"
   		+ "Execption(s): ResourceAlreadyExistsException is thrown when the id or the name and the address id does match an existing restaurant in the database",
   	response = ResponseEntity.class, produces = "application/json")
	public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant newRestaurant) throws ResourceAlreadyExistsException {
		if(service.existsById(newRestaurant.getId())) {
			throw new ResourceAlreadyExistsException("Restaurant with id= " + newRestaurant.getId() + " already exists.");
		}
		if(service.existsByNameAndAddresses(newRestaurant.getName(), newRestaurant.getAddresses())) {
			throw new ResourceAlreadyExistsException("Restaurant with name= " + newRestaurant.getName() + " and address= " + newRestaurant.getAddresses() + " already exists.");
		}
		Restaurant created = service.insert(newRestaurant);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}
	/**
	 * Deletes a restaurant from the database.
	 * @author Lori White
	 * @param id the restaurant id to search for 
	 * @return ResponseEntity - a response of was accepted and the deleted restaurant
	 * @throws ResourceNotFoundException is thrown when the id does not match an existing restaurant in the database
	 */
	@DeleteMapping("/delete/restaurant/{id}")
	@ApiOperation( value = "", 
	notes = "Deletes a restaurant from the database.\n"
   		+ "Usage: provide an id to remove a restaurant from the database\n"
   		+ "Author(s): Lori White\n"
   		+ "Execption(s): ResourceNotFoundException is thrown when the id does not match an existing restaurant in the database",
   	response = ResponseEntity.class, produces = "application/json")
	public ResponseEntity<Restaurant> deleteRestaurant(@PathVariable Long id) throws ResourceNotFoundException {
		Optional<Restaurant> deleted = service.findById(id);
		if(deleted.isEmpty()) {
			throw new ResourceNotFoundException("Restaurant with id= " + id + " does not exist.");
		} 
		service.deleteById(id);
		return new ResponseEntity<>(deleted.get(), HttpStatus.ACCEPTED);
	}
	//add all the patches
	/**
	 * Updates the name of a restaurant.
	 * @author Lori White
	 * @param addressStreet a map that holds a restaurant id of the restaurant and the restaurant's name to update
	 * @return ResponseEntity - a response of was accepted and the updated restaurant
	 * @throws ResourceNotFoundException is thrown when the id does not match an existing restaurant in the database
	 */
	@PatchMapping("/patch/restaurant/name")
	@ApiOperation( value = "", 
	notes = "Updates the name of a restaurant.\n"
  		+ "Usage: provide a map that holds a restaurant id of the restaurant and the restaurant's name to update in the database\n"
  		+ "Author(s): Lori White\n"
  		+ "Execption(s): ResourceNotFoundException is thrown when the id does not match an existing restaurant in the database",
  	response = ResponseEntity.class)
	public ResponseEntity<Restaurant> patchRestaurantName(@RequestBody Map<String, String> restaurantName) throws ResourceNotFoundException {
		Long id = Long.parseLong(restaurantName.get("id"));
		String newName = restaurantName.get("name");
		Optional<Restaurant> restaurant = service.findById(id);
		if(restaurant.isEmpty()) {
			throw new ResourceNotFoundException("Restaurant with id= " + id + " was not found.");
		}
		Restaurant updated = restaurant.get();
		updated.setName(newName);
		
		Query query = new Query();
        query.addCriteria(Criteria.where("id").is(updated.getId()));
        Update update = new Update();
        update.set("name", updated.getName());
        updated = mongoTemplate.findAndModify(query, update, options().returnNew(true).upsert(true), Restaurant.class);
		
		return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
	}
//	/**
//	 * Updates the city of an address.
//	 * @author Lori White
//	 * @param addressCity a map that holds an address id of the address and the address's city to update
//	 * @return ResponseEntity - a response of was accepted and the updated address
//	 * @throws ResourceNotFoundException is thrown when the id does not match an existing address in the database
//	 */
//	@PatchMapping("/patch/address/city")
//	@ApiOperation( value = "", 
//	notes = "Updates the city of an address.\n"
//  		+ "Usage: provide a map that holds an address id of the address and the address's city to update in the database\n"
//  		+ "Author(s): Lori White\n"
//  		+ "Execption(s): ResourceNotFoundException is thrown when the id does not match an existing address in the database",
//  	response = ResponseEntity.class)
//	public ResponseEntity<Address> patchAddressCity(@RequestBody Map<String, String> addressCity) throws ResourceNotFoundException {
//		Long id = Long.parseLong(addressCity.get("id"));
//		String newCity = addressCity.get("city");
//		Optional<Address> address = service.findById(id);
//		if(address.isEmpty()) {
//			throw new ResourceNotFoundException("Address with id= " + id + " was not found.");
//		}
//		Address updated = address.get();
//		updated.setCity(newCity);
//		service.save(updated);
//		return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
//	}
//	/**
//	 * Updates the state of an address.
//	 * @author Lori White
//	 * @param addressState a map that holds an address id of the address and the address's state to update
//	 * @return ResponseEntity - a response of was accepted and the updated address
//	 * @throws ResourceNotFoundException is thrown when the id does not match an existing address in the database
//	 */
//	@PatchMapping("/patch/address/state")
//	@ApiOperation( value = "", 
//	notes = "Updates the state of an address.\n"
//  		+ "Usage: provide a map that holds an address id of the address and the address's state to update in the database\n"
//  		+ "Author(s): Lori White\n"
//  		+ "Execption(s): ResourceNotFoundException is thrown when the id does not match an existing address in the database",
//  	response = ResponseEntity.class)
//	public ResponseEntity<Address> patchAddressState(@RequestBody Map<String, String> addressState) throws ResourceNotFoundException {
//		Long id = Long.parseLong(addressState.get("id"));
//		String newState = addressState.get("state");
//		Optional<Address> address = service.findById(id);
//		if(address.isEmpty()) {
//			throw new ResourceNotFoundException("Address with id= " + id + " was not found.");
//		}
//		Address updated = address.get();
//		updated.setState(newState);
//		service.save(updated);
//		return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
//	}
//	/**
//	 * Updates the zip of an address.
//	 * @author Lori White
//	 * @param addressZip a map that holds an address id of the address and the address's zip to update
//	 * @return ResponseEntity - a response of was accepted and the updated address
//	 * @throws ResourceNotFoundException is thrown when the id does not match an existing address in the database
//	 */
//	@PatchMapping("/patch/address/zip")
//	@ApiOperation( value = "", 
//	notes = "Updates the zip of an address.\n"
//  		+ "Usage: provide a map that holds an address id of the address and the address's zip to update in the database\n"
//  		+ "Author(s): Lori White\n"
//  		+ "Execption(s): ResourceNotFoundException is thrown when the id does not match an existing address in the database",
//  	response = ResponseEntity.class)
//	public ResponseEntity<Address> patchAddressZip(@RequestBody Map<String, String> addressZip) throws ResourceNotFoundException {
//		Long id = Long.parseLong(addressZip.get("id"));
//		String newZip = addressZip.get("zip");
//		Optional<Address> address = service.findById(id);
//		if(address.isEmpty()) {
//			throw new ResourceNotFoundException("Address with id= " + id + " was not found.");
//		}
//		Address updated = address.get();
//		updated.setZip(newZip);
//		service.save(updated);
//		return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
//	}
}
