package com.srikanthgr.vehicle_insurance_system;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String homeForm(Model model){
        
        model.addAttribute("home", new Home());
        return "home";
    }

    @PostMapping("/home")
    public String homeFormSubmit(@ModelAttribute Home home, Model model){

        model.addAttribute("home", home);
        return "result";
    }
}