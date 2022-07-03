package com.srikanthgr.vehicle_insurance_system;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long ID;

    @Column(nullable = false, unique = false, length = 255)
    private String firstName;

    @Column(nullable = false, unique = false, length = 255)
    private String lastName;

    @Column(nullable = false, unique = false, length = 255)
    private String address;

    @Column(nullable = false, unique = false, length = 255)
    private String city;

    @Column(nullable = false, unique = true, length = 255)
    private String username;

    @Column(nullable = false, unique = false, length = 255)
    private String password;

    @Column(nullable = false, unique = false, length = 255)
    private String confirmPassword;


    public Long getID() {
        return this.ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }    
}
