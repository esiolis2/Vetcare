package au.edu.rmit.sept.webapp.controllers;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class  HomeController {

    public HomeController() {
    }

    @GetMapping("/")
    public String index() {
        return "HomePage.html";
    }

    @GetMapping("/resources")
    public String Resources() {
        return "Resources.html";
    }


    @GetMapping("/signup")
    public String SignUp() {
        return "SignUp.html";
    }

    @GetMapping("/view-vaccination-records")
    public String ViewVaccinationRecords(){
        return"ViewVaccinationRecords.html";
    }


    @GetMapping("/login")
    public String Login() {
        return "Login.html";
    }

}
