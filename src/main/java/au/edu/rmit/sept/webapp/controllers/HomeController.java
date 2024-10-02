package au.edu.rmit.sept.webapp.controllers;


import au.edu.rmit.sept.webapp.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class  HomeController {

    public HomeController() {
    }

    @GetMapping("/")
    public String index() {
        return "HomePage.html";
    }

    
    @GetMapping("/signup")
    public String SignUp() {
        return "SignUp.html";
    }


    @GetMapping("/home-view-vaccination-records")
    public String ViewVaccinationRecords(){
        return"ViewVaccinationRecords.html";
    }

    @GetMapping("/home-treatment-plan")
    public String ViewTreatment(){
        return"ViewVaccinationRecords.html";
    }


    @GetMapping("/login")
    public String Login() {
        return "Login.html";
    }


//
    @ModelAttribute("loggedInUser")
    public User getLoggedInUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("loggedInUser");
    }

//  To ensure that these attributes are available in the model for the thyemleaf template to use these attributes for conditional rendering
    @ModelAttribute("userType")
    public String getUserType(HttpServletRequest request) {
        return (String) request.getSession().getAttribute("userType");
    }

}

