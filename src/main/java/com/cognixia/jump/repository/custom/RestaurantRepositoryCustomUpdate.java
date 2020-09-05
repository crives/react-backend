package com.cognixia.jump.repository.custom;

import com.cognixia.jump.model.Restaurant;

/**
 * A custom Repository interface for custom queries.
 * @author Lori White
 * @version v1 (09/02/2020)
 */
public interface RestaurantRepositoryCustomUpdate {
	//added by Lori White
	Restaurant patch(Long id, String field, Object value);
}
