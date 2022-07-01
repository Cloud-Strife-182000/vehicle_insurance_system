package com.srikanthgr.vehicle_insurance_system;

import javax.persistence.*;

@Entity
@Table(name = "vehicles")
public class Vehicle{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long ID;

    @Column(nullable = false, unique = false, length = 255)
    private String username;

    @Column(nullable = false, unique = false, length = 255)
    private String vehicleNumber;

    @Column(nullable = false, unique = false, length = 255)
    private String vehicleType;

    @Column(nullable = false, unique = false, length = 255)
    private String vehicleModel;

    @Column(nullable = false, unique = false, length = 255)
    private String registeredCity;

    @Column(nullable = false, unique = false, length = 255)    
    private String insuranceStatus;


    public Long getID(){
        return ID;
    }

    public void setID(Long ID){
        this.ID = ID;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getVehicleNumber(){
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber){
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleType(){
        return vehicleType;
    }

    public void setVehicleType(String vehicleType){
        this.vehicleType = vehicleType;
    }

    public String getVehicleModel(){
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel){
        this.vehicleModel = vehicleModel;
    }

    public String getRegisteredCity(){
        return registeredCity;
    }

    public void setRegisteredCity(String registeredCity){
        this.registeredCity = registeredCity;
    }

    public String getInsuranceStatus(){
        return insuranceStatus;
    }

    public void setInsuranceStatus(String insuranceStatus){
        this.insuranceStatus = insuranceStatus;
    }

}