package au.edu.rmit.sept.webapp.controllers;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "Welcome to Vet Care!");
        model.addAttribute("today", new Date()); // not a good approach for testing. More later.
        return "HomePage.html";

    }

}
