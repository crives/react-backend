package com.cognixia.jump.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{

	@Query("select r from Restaurant r where r.name = ?1")
	Restaurant findRestaurantByName(String name);
}
