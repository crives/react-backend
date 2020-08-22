package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Restaurant implements Serializable {

	private static final long serialVersionUID = 6041514973117274821L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "restaurantId")
	private Long id;

	@Column(columnDefinition = "char(50)")
	@NotNull(message = "Restaurant name must not be null")
	private String name;

	@Column
	private String imageUrl;

	@Column
	private String menuLink;

	@Column(columnDefinition = "char(50)")
	private String owner;

	@Column(columnDefinition = "char(16)")
	@Pattern(regexp = "^\\(\\d{3}\\)\\s?\\d{3}-\\d{4}$")
	private String phoneNumber;

	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
	private List<Address> addresses;
	
	public Restaurant() {
		this(-1L, "N/A", "N/A", "N/A", "N/A", "N/A", new ArrayList<Address>());
	}

	public Restaurant(Long id, @NotNull(message = "Restaurant name must not be null") String name, String imageUrl,
			String menuLink, String owner, @Pattern(regexp = "^\\(\\d{3}\\)\\s?\\d{3}-\\d{4}$") String phoneNumber,
			List<Address> addresses) {
		super();
		this.id = id;
		this.name = name;
		this.imageUrl = imageUrl;
		this.menuLink = menuLink;
		this.owner = owner;
		this.phoneNumber = phoneNumber;
		this.addresses = addresses;
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

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		addresses.addAll(addresses);
	}

	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", name=" + name + ", imageUrl=" + imageUrl + ", menuLink=" + menuLink
				+ ", owner=" + owner + ", phoneNumber=" + phoneNumber + ", addresses=" + addresses + "]";
	}

}
