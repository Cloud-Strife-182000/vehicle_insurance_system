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

    public Long getID(){
        return ID;
    }

    public void setID(Long ID){
        this.ID = ID;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setlastName(String lastName){
        this.lastName = lastName;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getCity(){
        return city;
    }

    public void setCity(String city){
        this.city = city;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
