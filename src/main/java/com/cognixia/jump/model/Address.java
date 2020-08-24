package com.cognixia.jump.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

@Entity
public class Address implements Serializable {

	private static final long serialVersionUID = 3622214286985572253L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "addressId")
	private Long id;

	@Column
	@Pattern(regexp = "^[0-9]+\\s[a-zA-Z]\\s*$")
	private String street;

	@Column
	private String city;

	@Column(columnDefinition = "char(2)")
	@Pattern(regexp = "^[A-Z]{2}$")
	private String state;

	@Column(columnDefinition = "char(5)")
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
