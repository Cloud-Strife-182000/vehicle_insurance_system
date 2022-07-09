package com.srikanthgr.vehicle_insurance_system;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface VehicleRepository extends JpaRepository<Vehicle, Long>{
    
    @Query("SELECT v FROM Vehicle v WHERE v.username = ?1")
    public List<Vehicle> findByUsername(String username);

    @Modifying
    @Transactional
    @Query("UPDATE Vehicle v SET v.insuranceStatus = ?2 WHERE v.vehicleNumber = ?1")
    public void updateInsuranceStatus(String vehicleNumber, String insuranceStatus);

    @Modifying
    @Transactional
    @Query("DELETE FROM Vehicle v WHERE v.vehicleNumber = ?1")
    public void removeVehicle(String vehiclenumber);
}
