package com.cognixia.jump.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Address;

/**
 * The Repository for Reviews.
 * @author Darreal Chambers and Lori White
 * @version v2 (08/29/2020)
 */
@Repository
public interface AddressRepository extends MongoRepository<Address, Long>{
	//added by Lori White
	boolean existsByStreetAndZip(String street, String zip);

}
