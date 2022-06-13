package com.srikanthgr.vehicle_insurance_system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/register")
    public String registrationForm(Model model){
        
        model.addAttribute("register", new UserDetails());
        return "register";
    }

    @PostMapping("/register")
    public String registrationFormSubmit(@ModelAttribute UserDetails userDetails, Model model){

        model.addAttribute("register", userDetails);

        String sqlQuery = "SELECT ID FROM users WHERE id=(SELECT max(id) FROM users)";

        int currentID = DBMSUtils.GetPreviousID(jdbcTemplate, sqlQuery) + 1;
        
        String sql = "INSERT INTO users (ID, FirstName, LastName) VALUES (";
        String id =  Integer.toString(currentID) + ", ";
        String firstName = "'" + userDetails.getFirstName() + "'" + ", "; 
        String lastName = "'" + userDetails.getLastName() + "'";
        String end = " );";

        sql = sql + id + firstName + lastName + end;
        
        jdbcTemplate.update(sql);

        return "result";
    }

    @GetMapping("/home")
    public String homePage(){
        return "home";
    }
}