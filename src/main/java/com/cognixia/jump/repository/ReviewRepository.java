package com.cognixia.jump.repository;

import java.util.List;

import com.cognixia.jump.repository.custom.UserRepositoryCustomUpdate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Review;

/**
 * The Repository for Reviews.
 * @author Lori White
 * @version v3 (08/31/2020)
 */
@Repository
public interface ReviewRepository extends MongoRepository<Review, Long> {
	//added by Lori White
	boolean existsByRestaurantId(Long restaurantId);
	//added by Lori White
	List<Review> findByRestaurantId(Long restaurantId);
	//added by Lori White
	boolean existsByRestaurantIdAndUserId(Long restaurantId, Long userId);
}
