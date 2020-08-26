package com.cognixia.jump.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.mongodb.repository.Query;

import com.cognixia.jump.model.Restaurant;

public interface RestaurantRepository extends MongoRepository<Restaurant, String>{

	//@Query("select r from Restaurant r where r.name = ?1")
	Restaurant findByName(String name);
}
