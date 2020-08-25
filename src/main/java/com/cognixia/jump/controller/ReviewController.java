package com.cognixia.jump.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Review;
import com.cognixia.jump.repository.ReviewRepository;

/**
 * The controller for the Reviews. 
 * @author Lori White
 * @version v2 (08/25/2020)
 */
@RequestMapping("/api")
@RestController
public class ReviewController {
	@Autowired
	ReviewRepository service;
	
	/**
	 * Retrieves a list of reviews pertaining to a restaurant id.
	 * @author Lori White
	 * @param id the restaurant id to search for
	 * @return List - the reviews in the database pertaining to the restaurant id
	 * @throws ResourceNotFoundException is thrown when the restaurant id does not match an existing review's restaurant id in the database
	 */
	@GetMapping("/reviews/restaurant/{id}") 
	public List<Review> getReviewsByRestaurantID(@PathVariable long id) throws ResourceNotFoundException {
		if(!service.existsByRestaurantId(id)) {
			throw new ResourceNotFoundException("There are no reviews for the restaurant with id= " + id);
		}
		return service.findByRestaurantId(id);
	}
}
