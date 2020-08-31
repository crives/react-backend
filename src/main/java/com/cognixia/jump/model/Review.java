package com.cognixia.jump.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reviews")
public class Review implements Serializable {
	private static final long serialVersionUID = -4458870115303573931L;
	@Transient
	public static final String SEQUENCE_NAME = "reviews_sequence";

	
	@Id
	@NotNull
	private Long id;
	private String description;
	@NotNull
	private Double rating;
	@NotNull
	private Long restaurantId;
	@NotNull
	private Long userId;
	
	public Review() {
		this("N/A", 0.00, -1L, -1L);
	}
	
	public Review(String description, Double rating, Long restaurantId, Long userId) {
		super();
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

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
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
