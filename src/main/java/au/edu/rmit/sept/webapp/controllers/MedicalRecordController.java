package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.models.PetInformation;
import au.edu.rmit.sept.webapp.services.PetInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Controller
public class MedicalRecordController {

    private final PetInformationService petInformationService;

    @Autowired
    public MedicalRecordController(PetInformationService petInformationService) {
        this.petInformationService = petInformationService;
    }

    @GetMapping("/access-medical-records")
    public String AccessMedicalRecords(Model model) {
        List<PetInformation> pets = petInformationService.getAllPets();
        model.addAttribute("pets", pets);
        return "AccessMedicalRecords";
    }

    @GetMapping("/viewMedicalRecord")
    public String viewMedicalRecord(@RequestParam("petId") Long petId, Model model) {
        PetInformation pet = petInformationService.getPetById(petId);
        if (pet != null) {
            model.addAttribute("pet", pet);
        }
        return "AccessMedicalRecords";
    }

//    //its working...
//    @GetMapping("/viewMedicalRecord")
//    @ResponseBody
//    public PetInformation viewMedicalRecord(@RequestParam("petId") Long petId) {
//        // Fetch the selected pet's information based on petId
//        return petInformationService.getPetById(petId);
//    }

    @GetMapping("/medicalHistoryDetails")
    public String showFullMedicalRecords() {
        return "FullMedicalRecords";
    }

    @GetMapping("/vaccinationDetails")
    public String showVaccinationDetails() {
        return "ViewVaccinationRecords";
    }

    @GetMapping("/treatmentPlanDetails")
    public String showTreatmentPlanDetails() {
        return "ViewTreatmentPlan";
    }
}



// Handle the POST request when a pet is selected to show its medical records
//    @PostMapping("/viewMedicalRecord")
//    public String viewMedicalRecord(@RequestParam("petId") Long petId, Model model) {
//        // Fetch the selected pet's information based on petId
//        PetInformation selectedPet = petInformationService.getPetById(petId);
//
//        // Add the selected pet to the model to display its details
//        model.addAttribute("pet", selectedPet);
//
//        // Fetch and add other related information like medical history, vaccination, etc.
//        // Assuming these are fetched from other services:
//        // MedicalHistory medicalHistory = medicalHistoryService.getLatestMedicalHistoryByPetId(petId);
//        // List<Vaccination> vaccinations = vaccinationService.getVaccinationHistoryByPetId(petId);
//
//        // model.addAttribute("medicalHistory", medicalHistory);
//        // model.addAttribute("vaccinations", vaccinations);
//
//        return "medicalRecordSummary";  // Thymeleaf view to display the selected pet's medical record
//    }


//    @GetMapping("/viewMedicalRecord")
//    public String viewMedicalRecord(Long petId, Model model) {
//        PetInformation pet = petInformationService.getPetById(petId);
//        model.addAttribute("pet", pet);
//        return "MedicalRecordDetails";
//    }



//    @GetMapping("/pets")
//    public String viewPets(Model model) {
//        List<PetInformation> pets = petInformationService.getAllPets();
//        model.addAttribute("pets", pets);
//        return "AccessMedicalRecords";
//    }
//
//    @PostMapping("/addPet")
//    public String createPetInformation(@ModelAttribute PetInformation petInformation) {
//        petInformationService.createPetInformation(petInformation);
//        return "redirect:/pets";
//    }
//
//    @GetMapping("/viewMedicalRecord")
//    public String viewPetMedicalRecords(Model model) {
//        Collection<PetInformation> pets = petInformationService.getAllPets(); // Ensure this method returns data
//        model.addAttribute("pets", pets); // Pass the data to the model
//        return "AccessMedicalRecords"; // The name of your HTML template file
//    }






















//
//    @GetMapping("/viewMedicalRecord")
//    public String viewMedicalRecord(@RequestParam(value = "petId", required = false) Long petId, Model model) {
//        // Fetch all pets to populate the pet selection dropdown
//        Collection<PetInformation> pets = petInformationService.getAllPets();
//        model.addAttribute("pets", pets);
//
//        // If a petId is provided, fetch the pet's information
//        if (petId != null) {
//            PetInformation pet = petInformationService.getPetById(petId);
//            model.addAttribute("pet", pet);  // Add the selected pet's information to the model
//        }
//
//        return "medicalRecordSummary";  // Return the corresponding Thymeleaf template
//    }

//package au.edu.rmit.sept.webapp.controllers;
//
//import au.edu.rmit.sept.webapp.models.PetInformation;
////import au.edu.rmit.sept.webapp.models.VaccinationRecord;
////import au.edu.rmit.sept.webapp.models.TreatmentPlan;
//import au.edu.rmit.sept.webapp.service.PetInformationService;
////import au.edu.rmit.sept.webapp.service.VaccinationService;
////import au.edu.rmit.sept.webapp.service.TreatmentPlanService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//public class MedicalRecordController {
//
//    private final PetInformationService petInformationService;
////    private final VaccinationService vaccinationService;
////    private final TreatmentPlanService treatmentPlanService;
//
//    @Autowired
//    public MedicalRecordController(PetInformationService petInformationService){ //, VaccinationService vaccinationService, TreatmentPlanService treatmentPlanService) {
//        this.petInformationService = petInformationService;
////        this.vaccinationService = vaccinationService;
////        this.treatmentPlanService = treatmentPlanService;
//    }
//
//    @GetMapping("/pets")
//    public String viewPets(Model model) {
//        List<PetInformation> pets = petInformationService.getAllPets();
//        model.addAttribute("pets", pets);
//        return "PetInformation";
//    }
//
//    @GetMapping("/pets/{id}/details")
//    public String viewPetDetails(@PathVariable Long id, Model model) {
//        PetInformation pet = petInformationService.getPetById(id).orElse(null);
////        if (pet != null) {
////            List<VaccinationRecord> vaccinations = vaccinationService.getVaccinationsByPetId(id);
////            List<TreatmentPlan> treatmentPlans = treatmentPlanService.getTreatmentPlansByPetId(id);
////            model.addAttribute("pet", pet);
////            model.addAttribute("vaccinations", vaccinations);
////            model.addAttribute("treatmentPlans", treatmentPlans);
////        }
//        return "PetDetails";
//    }
//
//    @GetMapping("/pets/{id}/medical-record")
//    public String viewMedicalRecord(@PathVariable Long id, Model model) {
//        PetInformation pet = petInformationService.getPetById(id).orElse(null);
//        if (pet != null) {
//            model.addAttribute("pet", pet);
//        }
//        return "MedicalRecord";
//    }
//
////    @PostMapping("/add-pet")
////    public String addPet(@ModelAttribute PetInformation petInformation) {
////        petInformationService.savePet(petInformation);
////        return "redirect:/pets";
////    }
//}