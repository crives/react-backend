package com.cognixia.jump.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.Restaurant;
import com.cognixia.jump.repository.RestaurantRepository;

@RequestMapping("/api")
@RestController
public class RestaurantController {

	@Autowired
	RestaurantRepository service;
	
	@GetMapping("/restaurants")
	public List<Restaurant> getAllRestaurants(){
		return service.findAll();
	}
	
	@GetMapping("/restaurant/{name}")
	public Restaurant getRestaurantByName(@PathVariable String name) {
		return service.findRestaurantByName(name);
	}
}
