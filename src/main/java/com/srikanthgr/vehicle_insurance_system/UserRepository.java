package com.srikanthgr.vehicle_insurance_system;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long>{
    
    @Query("SELECT u FROM User u WHERE u.username = ?1")
    public User findByUsername(String username);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.role = 'User' WHERE NOT u.id=1")
    public void fillRoles();
}
