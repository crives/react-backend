package com.cognixia.jump.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cognixia.jump.model.Address;

public interface AddressRepository extends MongoRepository<Address, String>{

	Object findById(long id);

}
