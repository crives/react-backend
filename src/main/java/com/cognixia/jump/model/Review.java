package com.cognixia.jump.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "review")
public class Review implements Serializable {
	private static final long serialVersionUID = -4458870115303573931L;
	
	@Id
	private Long id;
	private String description;
	private Float rating;
	private Long restaurantId;
	private Long userId;
	
	public Review(Long id, String description, Float rating, Long restaurantId, Long userId) {
		super();
		this.id = id;
		this.description = description;
		this.rating = rating;
		this.restaurantId = restaurantId;
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", description=" + description + ", rating=" + rating + ", restaurantId="
				+ restaurantId + ", userId=" + userId + "]";
	}
}
