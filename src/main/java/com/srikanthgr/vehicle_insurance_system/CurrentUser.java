package com.srikanthgr.vehicle_insurance_system;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class CurrentUser {
    
    private User user;
    private boolean loggedIn = false;

    public void SetCurrentUser(User user){
        this.user = user;

        if(user != null) SetLoggedInValue(true);
    }

    public User getCurrentUser(){
        return this.user;
    }

    public void SetLoggedInValue(boolean value){
        loggedIn = value;
    }

    public boolean getLoggedInValue(){
        return loggedIn;
    }
}
