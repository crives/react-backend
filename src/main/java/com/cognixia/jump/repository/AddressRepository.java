package com.cognixia.jump.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Address;

/**
 * The Repository for Reviews.
 * @author Darreal Chambers and Lori White
<<<<<<< HEAD
 * @version v3 (08/31/2020)
=======
 * @version v2 (08/29/2020)
>>>>>>> refs/heads/David
 */
@Repository
public interface AddressRepository extends MongoRepository<Address, Long>{
	//added by Lori White
	boolean existsByStreetAndZip(String street, String zip);
	//added by Lori White
	Address findByStreetAndZip(String street, String zip);
}
