package com.srikanthgr.vehicle_insurance_system;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VehicleRepository extends JpaRepository<Vehicle, Long>{
    
    @Query("SELECT v FROM Vehicle v WHERE v.username = ?1")
    public List<Vehicle> findByUsername(String username);
}
