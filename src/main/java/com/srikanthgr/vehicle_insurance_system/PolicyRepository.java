package com.srikanthgr.vehicle_insurance_system;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PolicyRepository extends JpaRepository<Policy, Long>{
    
    @Query("SELECT p FROM Policy p WHERE p.vehicleNumber = ?1")
    public Policy findByVehicleNumber(String vehicleNumber);
}
