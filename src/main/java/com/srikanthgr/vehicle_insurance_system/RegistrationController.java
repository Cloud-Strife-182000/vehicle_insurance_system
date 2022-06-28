package com.srikanthgr.vehicle_insurance_system;

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

    @GetMapping("/home")
    public String homePage(){
        return "home";
    }

    @GetMapping("/register")
    public String registrationForm(Model model){
        
        model.addAttribute("register", new User());
        model.addAttribute("logger", new Logger());

        return "register";
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

            return "register_success";
        }

        logger.setErrorMessage("Username already taken.");

        return "register_failure";
    }

    @GetMapping("/userlogin")
    public String loginForm(Model model){
        
        model.addAttribute("userlogin", new User());
        return "userlogin";
    }

    @PostMapping("/userlogin")
    public String loginFormSubmit(@ModelAttribute User user, HttpSession session, Model model){

        model.addAttribute("userlogin", user);

        String enteredUsername = user.getUsername();

        boolean successfulLogin = false;

        User userData = userRepo.findByUsername(enteredUsername);

        CurrentUser currUser = new CurrentUser();
        currUser.SetCurrentUser(userData);

        session.setAttribute("curr_user", currUser);

        if(userData != null){

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            String enteredPassword = user.getPassword();
            String actualPassword = userData.getPassword();

            successfulLogin = passwordEncoder.matches(enteredPassword, actualPassword);

            if(successfulLogin) return "login_success";
        }

        return "login_failure";
    }

    @GetMapping("/account")
    public String accountPage(HttpSession session, Model model){

        CurrentUser currUser = (CurrentUser) session.getAttribute("curr_user");

        if(currUser != null){

            if(currUser.getLoggedInValue() == true){

                User cu = currUser.getCurrentUser();

                model.addAttribute("account", cu);
                return "account";
            }
        }
        
        return "no_account";
    }

    @PostMapping("/account")
    public String logOutOfAccount(HttpSession session, Model model){

        CurrentUser currUser = (CurrentUser) session.getAttribute("curr_user");

        if(currUser != null){

            if(currUser.getLoggedInValue() == true){
        
                currUser = null;
                session.setAttribute("curr_user", currUser);

                model.addAttribute("account", null);
            }
        }

        return "no_account";
    }
}