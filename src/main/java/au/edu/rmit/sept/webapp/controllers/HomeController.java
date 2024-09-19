package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.services.PetInformationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/view-vaccination-records")
    public String ViewVaccinationRecords(){
        return"ViewVaccinationRecords.html";
    }


}
