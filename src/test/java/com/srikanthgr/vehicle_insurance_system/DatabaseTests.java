package com.srikanthgr.vehicle_insurance_system;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private PolicyRepository p_repo;

    @Test
    public void testCreateUser(){

        User user = new User();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode("password");

        user.setAddress("Kotturpuram");
        user.setCity("Chennai");
        user.setFirstName("Srikanth");
        user.setLastName("GR");
        user.setUsername("sri.gr81@gmail.com");
        user.setPassword(encodedPassword);
        user.setConfirmPassword(encodedPassword);

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

    @Test
    public void testCreatePolicy(){

        Policy p = new Policy();

        p.setUsername("sri.gr81@gmail.com");
        p.setVehicleNumber("A123");
        p.setParkingLocation("Locked Enclosure");
        p.setInsurancePolicy("Liability Policy");
        p.setNoClaimBonus("Yes");
        p.setAntiTheftDevice("Yes");
        p.setPaCoverNamedPerson("Yes");
        p.setPaCoverPassengers("Yes");
        p.setLegalLiability("Driver");
        p.setHigherDeductible("Yes");
        p.setHigherDeductibleValue(2500);
        p.setAutomobileAssociationMember("Yes");

        Policy savedPolicy = p_repo.save(p);

        Policy existPolicy = testEntityManager.find(Policy.class, savedPolicy.getID());

        assertThat(p.getUsername()).isEqualTo(existPolicy.getUsername());
    }
}
