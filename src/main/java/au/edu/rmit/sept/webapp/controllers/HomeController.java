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

//    @GetMapping("/userProfile")
//    public String userProfile() {
//        return "Profile";
//    }


    @GetMapping("/view-vaccination-records")
    public String ViewVaccinationRecords(){
        return"ViewVaccinationRecords.html";
    }

    @GetMapping("/login")
    public String Login(Model model) {
        model.addAttribute("message", "Welcome to Vet Care!");
        model.addAttribute("today", new Date()); // not a good approach for testing. More later.
        return "Login.html";
    }

}





//    //Testing Example only
//    @GetMapping("/access-medical-records")
//    public String AccessMedicalRecords(Model model) {
//        // Sample medical records using Map<String, Object> instead of a Record model
//        List<Map<String, Object>> records = Arrays.asList(
//                createRecord("Annual Checkup", 1234, "Dog, Age 3", 5678, "Rabies", "VetCare Clinic", "Dr. Smith", new Date()),
//                createRecord("Vaccination Update", 2345, "Cat, Age 5", 6789, "Feline Leukemia", "Healthy Paws", "Dr. Green", new Date()),
//                createRecord("Dental Cleaning", 3456, "Dog, Age 2", 7890, "N/A", "Pet Health Clinic", "Dr. Brown", new Date()),
//                createRecord("Heartworm Test", 4567, "Dog, Age 4", 8901, "Heartworm", "Healthy Pets Clinic", "Dr. White", new Date())
//        );
//
//        model.addAttribute("records", records);
//        return "AccessMedicalRecords.html";
//    }

    // Helper method to create a record
//    private Map<String, Object> createRecord(String recordName, int recordNumber, String petDetails, int treatmentPlanNumber,
//                                             String vaccinationName, String clinicName, String vetName, Date date) {
//        Map<String, Object> record = new HashMap<>();
//        record.put("recordName", recordName);
//        record.put("recordNumber", recordNumber);
//        record.put("petDetails", petDetails);
//        record.put("treatmentPlanNumber", treatmentPlanNumber);
//        record.put("vaccinationName", vaccinationName);
//        record.put("clinicName", clinicName);
//        record.put("vetName", vetName);
//        record.put("date", date);
//        return record;
//    }

    //End Access Medical Testing, can delete later


