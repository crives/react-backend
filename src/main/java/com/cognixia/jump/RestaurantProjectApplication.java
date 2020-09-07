package com.cognixia.jump;

import com.cognixia.jump.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * The Restaurant Reviews Application.
 * @author David Morales, Darreal Chambers, and Lori White
 * @version v2 (08/30/2020)
 */
@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = UserRepository.class)
public class RestaurantProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantProjectApplication.class, args);
	}

}
