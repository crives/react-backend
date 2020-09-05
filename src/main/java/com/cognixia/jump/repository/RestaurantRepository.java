package com.cognixia.jump.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Restaurant;
import com.cognixia.jump.repository.custom.RestaurantRepositoryCustomUpdate;

/**
 * The Repository for Restaurant.
 * @author Darreal Chambers and Lori White
 * @version v3 (09/02/2020)
 */
@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant, Long>, RestaurantRepositoryCustomUpdate{
	//added by Darreal Chambers
	//@Query("select r from Restaurant r where r.name = ?1")
	//Restaurant findByName(String name);
	//added by Lori White
	boolean existsByNameAndAddressId(String name, Long addressId);
}
