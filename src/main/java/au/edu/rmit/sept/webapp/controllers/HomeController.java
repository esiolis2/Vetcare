package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.services.PetInformationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Controller
public class  HomeController {

    public HomeController(PetInformationService petInformationService) {
    }

    @GetMapping("/")
    public String index() {
      return "HomePage.html";
    }

    @GetMapping("/resources")
    public String Resources() {
        return "Resources.html";
    }

    @GetMapping("/view-treatment-plan")
    public String  ViewTreatmentPlan() {
        return "ViewTreatmentPlan.html";
    }

    @GetMapping("/signup")
    public String SignUp() {
       return "SignUp.html";
    }

    @GetMapping("/login")
    public String Login(Model model) {
        model.addAttribute("message", "Welcome to Vet Care!");
        model.addAttribute("today", new Date()); // not a good approach for testing. More later.
        return "Login.html";
    }




}
