package au.edu.rmit.sept.webapp.controllers;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class  HomeController {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "Welcome to Vet Care!");
        model.addAttribute("today", new Date()); // not a good approach for testing. More later.
        return "HomePage.html";
    }

    @GetMapping("/resources")
    public String Resources(Model model) {
        model.addAttribute("message", "Welcome to Vet Care!");
        model.addAttribute("today", new Date()); // not a good approach for testing. More later.
        return "Resources.html";
    }

    @GetMapping("/view-treatment-plan")
    public String  ViewTreatmentPlan(Model model) {
        model.addAttribute("message", "Welcome to Vet Care!");
        model.addAttribute("today", new Date()); // not a good approach for testing. More later.
        return "ViewTreatmentPlan.html";
    }

    @GetMapping("/signup")
    public String SignUp(Model model) {
        model.addAttribute("message", "Welcome to Vet Care!");
        model.addAttribute("today", new Date()); // not a good approach for testing. More later.
        return "SignUp.html";
    }

    @GetMapping("/login")
    public String Login(Model model) {
        model.addAttribute("message", "Welcome to Vet Care!");
        model.addAttribute("today", new Date()); // not a good approach for testing. More later.
        return "Login.html";
    }

}
