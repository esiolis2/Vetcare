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

    //Route for dashboard page and dashboard content
    @GetMapping("/account")
    public String accountDashboard(Model model) {
        model.addAttribute("message", "Welcome to Vet Care Dashboard!");
        return "Account"; // This refers to the 'Account.html'
    }

    // User Dashboard Page
    @GetMapping("/dashboard/appointment-management")
    public String appointmentManagement() {
        return "dashboard/AppointmentManagement"; // Refers to 'UserDashboard.html'
    }
    @GetMapping("/dashboard/prescription-management")
    public String prescriptionManagement() {
        return "dashboard/PrescriptionManagement"; // This returns 'PrescriptionManagement.html'
    }

    @GetMapping("/dashboard/pet-management")
    public String petManagement() {
        return "dashboard/PetManagement"; // This returns 'PetManagement.html'
    }

}
