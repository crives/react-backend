package com.cognixia.jump.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Address;

@Repository
public interface AddressRepository extends MongoRepository<Address, Long>{

}
