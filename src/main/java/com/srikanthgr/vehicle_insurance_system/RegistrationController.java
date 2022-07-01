package com.srikanthgr.vehicle_insurance_system;

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

    @GetMapping("/home")
    public String homePage(){
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

        if(userData == null){

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            userRepo.save(user);

            return "/register_success";
        }

        logger.setErrorMessage("Username already taken.");

        return "/register_failure";
    }

    @GetMapping("/userlogin")
    public String loginForm(Model model){

        model.addAttribute("userlogin", new User());

        return "/userlogin";
    }

    @PostMapping("/userlogin")
    public String loginFormSubmit(@ModelAttribute User user, HttpSession session, Model model){

        model.addAttribute("userlogin", user);

        String enteredUsername = user.getUsername();

        boolean successfulLogin = false;

        User userData = userRepo.findByUsername(enteredUsername);

        session.setAttribute("curr_user", userData);

        if(userData != null){

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            String enteredPassword = user.getPassword();
            String actualPassword = userData.getPassword();

            successfulLogin = passwordEncoder.matches(enteredPassword, actualPassword);

            if(successfulLogin) return "/login_success";
        }

        return "/login_failure";
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

        if(currUser != null){

            currUser = null;

            session.setAttribute("curr_user", null);
            model.addAttribute("account", null);
        }

        return "/no_account";
    }

    @GetMapping("/account/insurance")
    public String insurancePage(HttpSession session, Model model){
        
        User currUser = (User) session.getAttribute("curr_user");

        List<Vehicle> currVehicles = vehicleRepo.findByUsername(currUser.getUsername());

        if(!currVehicles.isEmpty()){

            model.addAttribute("vehicles", currVehicles);

            return "/account/insurance";
        }

        return "/account/no_vehicle";
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

        return "/account/register_policy";
    }

    @GetMapping("/account/register_policy")
    public String policyForm(HttpSession session, Model model){
        
        model.addAttribute("policy_details", new Vehicle());

        return "/account/register_policy";
    }
}