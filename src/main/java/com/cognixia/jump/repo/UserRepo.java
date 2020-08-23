package com.cognixia.jump.repo;

import com.cognixia.jump.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<User, String> {
    Optional<User> findById(long userId);

    void deleteById(long id);
}
