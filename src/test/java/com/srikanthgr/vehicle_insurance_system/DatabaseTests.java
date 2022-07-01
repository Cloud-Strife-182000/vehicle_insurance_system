package com.srikanthgr.vehicle_insurance_system;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.annotation.Rollback;
import org.junit.jupiter.api.Test;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class DatabaseTests {
    
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository repo;

    @Autowired
    private VehicleRepository v_repo;

    @Test
    public void testCreateUser(){

        User user = new User();

        user.setAddress("Kotturpuram");
        user.setCity("Chennai");
        user.setFirstName("Srikanth");
        user.setlastName("GR");
        user.setUsername("sri.gr81@gmail.com");
        user.setPassword("password");

        User savedUser = repo.save(user);

        User existUser = testEntityManager.find(User.class, savedUser.getID());

        assertThat(user.getUsername()).isEqualTo(existUser.getUsername());
    }

    @Test
    public void testCreateVehicle(){

        Vehicle v = new Vehicle();

        v.setUsername("sri.gr81@gmail.com");
        v.setVehicleNumber("A123");
        v.setVehicleType("Scooter");
        v.setVehicleModel("Honda Activa 2");
        v.setRegisteredCity("Chennai");
        v.setInsuranceStatus("Not Insured");

        Vehicle savedVehicle = v_repo.save(v);

        Vehicle existVehicle = testEntityManager.find(Vehicle.class, savedVehicle.getID());

        assertThat(v.getUsername()).isEqualTo(existVehicle.getUsername());
    }
}
