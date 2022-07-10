package com.srikanthgr.vehicle_insurance_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.srikanthgr.vehicle_insurance_system"})
public class VehicleInsuranceSystemApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(VehicleInsuranceSystemApplication.class, args);
	}

}
