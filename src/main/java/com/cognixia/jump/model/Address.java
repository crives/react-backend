package com.cognixia.jump.model;

import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "address")
public class Address {

	public static long counter = 0;
	
	@Id
	private Long id;

	@Pattern(regexp = "^[0-9]+\\s[a-zA-Z]\\s*$")
	private String street;

	private String city;

	@Pattern(regexp = "^[A-Z]{2}$")
	private String state;

	@Pattern(regexp = "^\\d{5}$")
	private String zip;
	
	public Address() {
		this(-1L, "N/A", "N/A", "N/A", "N/A");
	}

	public Address(Long id, @Pattern(regexp = "^[0-9]+\\s[a-zA-Z]\\s*$") String street, String city,
			@Pattern(regexp = "^[A-Z]{2}$") String state, @Pattern(regexp = "^\\d{5}$") String zip) {
		super();
		this.id = id;
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
