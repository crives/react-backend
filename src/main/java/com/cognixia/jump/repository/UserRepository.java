package com.cognixia.jump.repository;

import com.cognixia.jump.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findById(Long userId);

    void deleteById(long id);
    
    Optional<User> findByEmail(String email);
}
