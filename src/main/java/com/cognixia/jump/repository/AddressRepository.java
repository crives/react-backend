package com.cognixia.jump.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cognixia.jump.model.Address;

public interface AddressRepository extends MongoRepository<Address, String>{


	Optional<Address> findById(Long id);

}
