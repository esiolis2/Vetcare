package au.edu.rmit.sept.webapp.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

}

