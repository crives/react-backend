package com.cognixia.jump.model;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "address")
public class Address implements Serializable {

	private static final long serialVersionUID = -6174792291767963142L;
	@Transient
	public static final String SEQUENCE_NAME = "address_sequence";
	
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
		this("N/A", "N/A", "N/A", "N/A");
	}

	public Address(@Pattern(regexp = "^[0-9]+\\s[a-zA-Z]\\s*$") String street, String city,
			@Pattern(regexp = "^[A-Z]{2}$") String state, @Pattern(regexp = "^\\d{5}$") String zip) {
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
