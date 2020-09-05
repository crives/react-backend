package com.cognixia.jump.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The model for Restaurants.
 * @author Darreal Chambers and Lori White
 * @version v2 (09/05/2020)
 */
@Document(collection = "restaurants")
public class Restaurant implements Serializable {

	private static final long serialVersionUID = 6041514973117274821L;
	@Transient
	public static final String SEQUENCE_NAME = "restaurants_sequence";
	
	@Id
	@NotNull
	private Long id;

	@NotNull(message = "Restaurant name must not be null")
	private String name;

	private String imageUrl;

	private String menuLink;
	@NotNull(message = "Restaurant owner must not be null")
	private String owner;

	@Pattern(regexp = "^\\(\\d{3}\\)\\s?\\d{3}-\\d{4}$")
	private String phoneNumber;
	@NotNull(message = "Restaurant address id must not be null")
	private Long addressId;
	
	/**
	 * The default constructor.
	 * @author Darreal Chambers and Lori White
	 */
	public Restaurant() {
		this("N/A", "N/A", "N/A", "N/A", "N/A", -1L);
	}
	/**
	 * The overloaded constructor.
	 * @author Darreal Chambers and Lori White
	 * @param name
	 * @param imageUrl
	 * @param menuLink
	 * @param owner
	 * @param phoneNumber
	 * @param addressId
	 */
	public Restaurant(@NotNull(message = "Restaurant name must not be null") String name, String imageUrl,
			String menuLink, @NotNull(message = "Restaurant owner must not be null") String owner, @Pattern(regexp = "^\\(\\d{3}\\)\\s?\\d{3}-\\d{4}$") String phoneNumber,
			@NotNull(message = "Restaurant address id must not be null") Long addressId) {
		super();
		this.name = name;
		this.imageUrl = imageUrl;
		this.menuLink = menuLink;
		this.owner = owner;
		this.phoneNumber = phoneNumber;
		this.addressId = addressId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getMenuLink() {
		return menuLink;
	}

	public void setMenuLink(String menuLink) {
		this.menuLink = menuLink;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", name=" + name + ", imageUrl=" + imageUrl + ", menuLink=" + menuLink
				+ ", owner=" + owner + ", phoneNumber=" + phoneNumber + ", addresses=" + addressId + "]";
	}
}
