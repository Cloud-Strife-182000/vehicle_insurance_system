package com.srikanthgr.vehicle_insurance_system;

import javax.persistence.*;

@Entity
@Table(name = "policies")
public class Policy {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long ID;

    @Column(nullable = false, unique = false, length = 255)
    private String username;

    @Column(nullable = false, unique = false, length = 255)
    private String vehicleNumber;

    @Column(nullable = false, unique = false)
    private String parkingLocation;

    @Column(nullable = true, unique = false)
    private String insurancePolicy;

    @Column(nullable = false, unique = false)
    private String noClaimBonus;

    @Column(nullable = false, unique = false)
    private String antiTheftDevice;

    @Column(nullable = false, unique = false)
    private String paCoverNamedPerson;

    @Column(nullable = false, unique = false)
    private String paCoverPassengers;

    @Column(nullable = false, unique = false)
    private String legalLiability;

    @Column(nullable = true, unique = false)
    private String higherDeductible;

    @Column(nullable = true, unique = false)
    private float higherDeductibleValue;

    @Column(nullable = false, unique = false)
    private String automobileAssociationMember;

    public Long getID() {
        return this.ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVehicleNumber() {
        return this.vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getParkingLocation() {
        return this.parkingLocation;
    }

    public void setParkingLocation(String parkingLocation) {
        this.parkingLocation = parkingLocation;
    }

    public String getInsurancePolicy() {
        return this.insurancePolicy;
    }

    public void setInsurancePolicy(String insurancePolicy) {
        this.insurancePolicy = insurancePolicy;
    }

    public String getNoClaimBonus() {
        return this.noClaimBonus;
    }

    public void setNoClaimBonus(String noClaimBonus) {
        this.noClaimBonus = noClaimBonus;
    }

    public String getAntiTheftDevice() {
        return this.antiTheftDevice;
    }

    public void setAntiTheftDevice(String antiTheftDevice) {
        this.antiTheftDevice = antiTheftDevice;
    }

    public String getPaCoverNamedPerson() {
        return this.paCoverNamedPerson;
    }

    public void setPaCoverNamedPerson(String paCoverNamedPerson) {
        this.paCoverNamedPerson = paCoverNamedPerson;
    }

    public String getPaCoverPassengers() {
        return this.paCoverPassengers;
    }

    public void setPaCoverPassengers(String paCoverPassengers) {
        this.paCoverPassengers = paCoverPassengers;
    }

    public String getLegalLiability() {
        return this.legalLiability;
    }

    public void setLegalLiability(String legalLiability) {
        this.legalLiability = legalLiability;
    }

    public String getHigherDeductible() {
        return this.higherDeductible;
    }

    public void setHigherDeductible(String higherDeductible) {
        this.higherDeductible = higherDeductible;
    }

    public float getHigherDeductibleValue() {
        return this.higherDeductibleValue;
    }

    public void setHigherDeductibleValue(float higherDeductibleValue) {
        this.higherDeductibleValue = higherDeductibleValue;
    }

    public String getAutomobileAssociationMember() {
        return this.automobileAssociationMember;
    }

    public void setAutomobileAssociationMember(String automobileAssociationMember) {
        this.automobileAssociationMember = automobileAssociationMember;
    }


    
}
