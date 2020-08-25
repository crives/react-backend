package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Restaurant;
import com.cognixia.jump.repository.RestaurantRepository;

/**
 * The controller for the Restaurants. 
 * @author Lori White
 * @version v2 (08/25/2020)
 */
@RequestMapping("/api")
@RestController
public class RestaurantController {
	@Autowired 
	RestaurantRepository service;
	
	/**
	 * Retrieves all the restaurants in the database and sorts them by name in ascending order.
	 * @author Lori White
	 * @return List - the restaurants in the database
	 */
	@GetMapping("/restaurants")
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
	public Restaurant getRestaurantById(@PathVariable String id) throws ResourceNotFoundException {
		Optional<Restaurant> restaurant = service.findById(id);
		if(restaurant.isEmpty()) {
			throw new ResourceNotFoundException("The restaurant with id= " + id + " does not exist in the database.");
		}
		return restaurant.get();
	}
}
