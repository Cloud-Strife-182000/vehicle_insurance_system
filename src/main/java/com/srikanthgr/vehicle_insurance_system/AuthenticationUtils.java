
package com.srikanthgr.vehicle_insurance_system;

import javax.servlet.http.HttpSession;

public class AuthenticationUtils{

    public static boolean isAdmin(HttpSession session){

        User userData = (User) session.getAttribute("curr_user");

        if(userData == null){

            session.setAttribute("isAdmin", false);
        }

        Object isAdminObject = session.getAttribute("isAdmin");

        boolean isAdminBool = false;
        
        if(isAdminObject != null){

            isAdminBool = (boolean) session.getAttribute("isAdmin");
        }

        return isAdminBool;
    } 

}