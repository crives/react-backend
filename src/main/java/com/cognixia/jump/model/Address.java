package com.cognixia.jump.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The model for Address.
 * @author Darreal Chambers and Lori White
 * @version v2 (09/05/2020)
 */
@Document(collection = "address")
public class Address implements Serializable {

	private static final long serialVersionUID = -6174792291767963142L;
	@Transient
	public static final String SEQUENCE_NAME = "address_sequence";
	
	@Id
	@NotNull
	private Long id;
	@NotNull(message = "Address street must not be null")
	@Pattern(regexp = "^[0-9]+\\s[a-zA-Z]\\s*$")
	private String street;
	@NotNull(message = "Address city must not be null")
	private String city;
	@NotNull(message = "Address state must not be null")
	@Pattern(regexp = "^[A-Z]{2}$")
	private String state;
	@NotNull(message = "Address zip must not be null")
	@Pattern(regexp = "^\\d{5}$")
	private String zip;
	
	/**
	 * The default constructor.
	 * @author Darreal Chambers and Lori White
	 */
	public Address() {
		this("N/A", "N/A", "N/A", "N/A");
	}
	/**
	 * The overloaded constructor.
	 * @author Darreal Chambers and Lori White
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 */
	public Address(@NotNull(message = "Address street must not be null") @Pattern(regexp = "^[0-9]+\\s[a-zA-Z]\\s*$") String street, @NotNull(message = "Address city must not be null") String city,
			@NotNull(message = "Address state must not be null") @Pattern(regexp = "^[A-Z]{2}$") String state, @NotNull(message = "Address zip must not be null") @Pattern(regexp = "^\\d{5}$") String zip) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", street=" + street + ", city=" + city + ", state=" + state + ", zip=" + zip
				+ "]";
	}
}
