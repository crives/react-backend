package com.cognixia.jump.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Restaurant;

@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant, Long>{

	//@Query("select r from Restaurant r where r.name = ?1")
	Restaurant findByName(String name);
}
