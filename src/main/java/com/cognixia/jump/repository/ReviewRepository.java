package com.cognixia.jump.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cognixia.jump.model.Address;

public interface ReviewRepository extends MongoRepository<Address, String>{

}
