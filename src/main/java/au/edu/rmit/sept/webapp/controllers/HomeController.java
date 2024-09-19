package au.edu.rmit.sept.webapp.controllers;

import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class  HomeController {
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
    public String Login() {
        return "Login.html";
    }

}
