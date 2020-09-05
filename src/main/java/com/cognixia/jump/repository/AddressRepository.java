package com.cognixia.jump.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Address;

/**
 * The Repository for Reviews.
 * @author Darreal Chambers and Lori White
 * @version v3 (08/31/2020)
 */
@Repository
public interface AddressRepository extends MongoRepository<Address, Long>{
	//added by Lori White
	boolean existsByStreetAndZip(String street, String zip);
	//added by Lori White
	Address findByStreetAndZip(String street, String zip);
}
