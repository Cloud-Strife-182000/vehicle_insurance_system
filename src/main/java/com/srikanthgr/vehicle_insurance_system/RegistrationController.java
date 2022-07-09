package com.srikanthgr.vehicle_insurance_system;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private VehicleRepository vehicleRepo;

    @Autowired
    private PolicyRepository policyRepo;

    @GetMapping("/home")
    public String homePage(Model model, HttpSession session){

        Object isAdminObject = session.getAttribute("isAdmin");
        boolean isAdmin = false;
        
        if(isAdminObject != null){

            isAdmin = (boolean) session.getAttribute("isAdmin");
        }

        model.addAttribute("isAdmin", isAdmin);

        return "/home";
    }

    @GetMapping("/register")
    public String registrationForm(Model model){

        model.addAttribute("register", new User());
        model.addAttribute("logger", new Logger());

        return "/register";
    }

    @PostMapping("/register")
    public String registrationFormSubmit(@ModelAttribute User user, @ModelAttribute Logger logger, Model model){

        model.addAttribute("register", user);
        model.addAttribute("logger", logger);

        String enteredUsername = user.getUsername();

        User userData = userRepo.findByUsername(enteredUsername);

        if(userData != null){

            logger.setErrorMessage("Error: Username already taken.");

            model.addAttribute("register", user);
            model.addAttribute("logger", logger);

            return "/register";
        }
        
        if(!user.getPassword().equals(user.getConfirmPassword())){

            logger.setErrorMessage("Error: Passwords do not match.");

            model.addAttribute("register", user);
            model.addAttribute("logger", logger);

            return "/register";
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setConfirmPassword(encodedPassword);

        userRepo.save(user);

        return "/register_success";
    }

    @GetMapping("/userlogin")
    public String loginForm(Model model){

        model.addAttribute("userlogin", new User());
        model.addAttribute("logger", new Logger());

        return "/userlogin";
    }

    @PostMapping("/userlogin")
    public String loginFormSubmit(@ModelAttribute User user, @ModelAttribute Logger logger, HttpSession session, Model model){

        String enteredUsername = user.getUsername();

        boolean successfulLogin = false;

        User userData = userRepo.findByUsername(enteredUsername);

        model.addAttribute("userlogin", user);

        session.setAttribute("curr_user", userData);

        if(userData == null){

            logger.setErrorMessage("Error: User not found.");

            model.addAttribute("logger", logger);

            return "/userlogin";
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String enteredPassword = user.getPassword();
        String actualPassword = userData.getPassword();

        successfulLogin = passwordEncoder.matches(enteredPassword, actualPassword);

        if(successfulLogin){

            if(userData.getRole().equals("Admin")){

                session.setAttribute("isAdmin", true);
            }
            else{
                session.setAttribute("isAdmin", false);
            }

            return "/login_success";
        }
        else{

            logger.setErrorMessage("Error: Wrong password.");

            model.addAttribute("logger", logger);

            return "/userlogin";
        }
    }

    @GetMapping("/account")
    public String accountPage(HttpSession session, Model model){

        User currUser = (User) session.getAttribute("curr_user");

        if(currUser != null){

            model.addAttribute("account", currUser);
            return "/account";
        }
        
        return "/no_account";
    }

    @PostMapping("/account")
    public String logOutOfAccount(HttpSession session, Model model){

        User currUser = (User) session.getAttribute("curr_user");

        if(currUser == null){
            return "/no_account";
        }

        currUser = null;

        session.setAttribute("curr_user", null);
        session.setAttribute("isAdmin", false);
        model.addAttribute("account", null);

        return "/no_account";
    }

    @GetMapping("/account/insurance")
    public String insurancePage(HttpSession session, Model model){
        
        User currUser = (User) session.getAttribute("curr_user");

        List<Vehicle> currVehicles = vehicleRepo.findByUsername(currUser.getUsername());
        List<Policy> currPolicies = new ArrayList<Policy>();

        Logger logger_v = new Logger();
        boolean vehiclesExists = true;

        Logger logger_p = new Logger();
        boolean policiesExists = true;

        //if no vehicles in database
        if(currVehicles.isEmpty()){

            vehiclesExists = false;
            logger_v.setErrorMessage("You have not registered any vehicles.");

            policiesExists = false;
            logger_p.setErrorMessage("You must first register a vehicle to apply for insurance.");
        }

        //if there are vehicles in database
        if(vehiclesExists == true){

            for (Vehicle vehicle : currVehicles) {
            
                if(vehicle.getInsuranceStatus().equals("Applied")){
    
                    Policy p = policyRepo.findByVehicleNumber(vehicle.getVehicleNumber());
                    currPolicies.add(p);
                }
            }
            
            //if vehicles are there but no policies in database
            if(currPolicies.isEmpty()){
                
                policiesExists = false;
                logger_p.setErrorMessage("You have not applied any vehicle for insurance.");
            }

        }

        model.addAttribute("vehicles", currVehicles);
        model.addAttribute("policies", currPolicies);
        model.addAttribute("logger_v", logger_v);
        model.addAttribute("logger_p", logger_p);
        model.addAttribute("vehiclesExists", vehiclesExists);
        model.addAttribute("policiesExists", policiesExists);

        return "/account/insurance";
    }

    @GetMapping("/account/register_vehicle")
    public String vehicleForm(HttpSession session, Model model){
        
        model.addAttribute("vehicle_details", new Vehicle());

        return "/account/register_vehicle";
    }

    @PostMapping("/account/register_vehicle")
    public String vehicleFormSubmit(@ModelAttribute Vehicle vehicle, HttpSession session, Model model){

        User currUser = (User) session.getAttribute("curr_user");

        vehicle.setUsername(currUser.getUsername());
        vehicle.setInsuranceStatus("Not Insured");

        model.addAttribute("vehicle_details", vehicle);

        vehicleRepo.save(vehicle);

        return "/account/vehicle_success";
    }

    @GetMapping("/account/register_policy")
    public String policyForm(HttpSession session, Model model){
        
        model.addAttribute("policy_details", new Policy());
        model.addAttribute("logger", new Logger());

        return "/account/register_policy";
    }

    @PostMapping("/account/register_policy")
    public String policyFormSubmit(@ModelAttribute Policy policy, @ModelAttribute Logger logger, HttpSession session, Model model){

        User currUser = (User) session.getAttribute("curr_user");

        policy.setUsername(currUser.getUsername());

        model.addAttribute("policy_details", policy);

        if(policy.getVehicleNumber().isBlank()){

            logger.setErrorMessage("Error: Vehicle number not specified. Please enter your vehicle number.");
            
            model.addAttribute("policy_details", policy);
            model.addAttribute("logger", logger);

            return "/account/register_policy";
        }

        List<Vehicle> currVehicles = vehicleRepo.findByUsername(currUser.getUsername());

        boolean vehicleRegistered = false;
        Vehicle currVehicle = new Vehicle();

        for (Vehicle vehicle : currVehicles) {
            
            if(vehicle.getVehicleNumber().equals(policy.getVehicleNumber())){

                vehicleRegistered = true;
                currVehicle = vehicle;
            }
        }

        if(!vehicleRegistered){

            logger.setErrorMessage("Error: Vehicle not registered.");
            
            model.addAttribute("policy_details", policy);
            model.addAttribute("logger", logger);

            return "/account/register_policy";
        }

        if(currVehicle.getInsuranceStatus().equals("Applied")){

            logger.setErrorMessage("You have already applied for insurance on this vehicle.");
            
            model.addAttribute("policy_details", policy);
            model.addAttribute("logger", logger);

            return "/account/register_policy";
        }

        vehicleRepo.updateInsuranceStatus(currVehicle.getVehicleNumber(), "Applied");

        policyRepo.save(policy);

        return "/account/policy_success";
    }

    @GetMapping("/account/removal")
    public String removalForm(Model model){

        model.addAttribute("vehicle_details", new Vehicle());
        model.addAttribute("logger", new Logger());

        return "/account/removal";
    }

    @PostMapping("/account/removal")
    public String removalForm(@ModelAttribute Vehicle v, @ModelAttribute Logger logger, HttpSession session, Model model){

        model.addAttribute("vehicle_details", v);

        //check if vehicle number field is blank
        if(v.getVehicleNumber().isBlank()){

            logger.setErrorMessage("Error: Vehicle number not specified. Please enter your vehicle number.");

            model.addAttribute("logger", logger);

            return "/account/removal";
        }

        User currUser = (User) session.getAttribute("curr_user");

        List<Vehicle> currVehicles = vehicleRepo.findByUsername(currUser.getUsername());

        boolean vehicleRegistered = false;
        Vehicle currVehicle = new Vehicle();

        for (Vehicle vehicle : currVehicles) {
            
            if(vehicle.getVehicleNumber().equals(v.getVehicleNumber())){

                vehicleRegistered = true;
                currVehicle = vehicle;
            }
        }

        //check if vehicle is in database
        if(!vehicleRegistered){

            logger.setErrorMessage("Error: Vehicle not registered.");
            
            model.addAttribute("logger", logger);

            return "/account/register_policy";
        }

        vehicleRepo.removeVehicle(currVehicle.getVehicleNumber());

        //check if vehicle has policy in database
        if(currVehicle.getInsuranceStatus().equals("Applied")){

            policyRepo.removePolicy(currVehicle.getVehicleNumber());
        }

        model.addAttribute("logger", logger);

        return "/account/removal_success";
    }

    @GetMapping("/admin")
    public String adminPage(Model model, HttpSession session){

        Object isAdminObject = session.getAttribute("isAdmin");
        boolean isAdmin = false;
        
        if(isAdminObject != null){

            isAdmin = (boolean) session.getAttribute("isAdmin");
        }

        model.addAttribute("isAdmin", isAdmin);

        return "/admin";
    }
}