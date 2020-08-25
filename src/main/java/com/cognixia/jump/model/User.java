package com.cognixia.jump.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {


    public static long idCounter = 0;
    public static long addressCounter = 0;

    public enum Role {
        ROLE_USER, ROLE_ADMIN
    }

    @Id
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Long addressId;
    private Role userRole;
    private boolean enable;

    public User() {
        this(-1L, "N/A", "N/A", "N/A", "N/A", -1L, Role.ROLE_USER, false);
    }

    public User(Long id, String email, String password, String firstName, String lastName, Long addressId, Role userRole, boolean enable) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressId = addressId;
        this.userRole = userRole;
        this.enable = enable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

    public static long getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(long idCounter) {
        User.idCounter = idCounter;
    }

    public static long getAddressCounter() {
        return addressCounter;
    }

    public static void setAddressCounter(long addressCounter) {
        User.addressCounter = addressCounter;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", addressId=" + addressId +
                ", userRole=" + userRole +
                ", enable=" + enable +
                '}';
    }
}
