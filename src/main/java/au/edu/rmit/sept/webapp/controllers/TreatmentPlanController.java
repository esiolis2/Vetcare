package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.models.PetInformation;
import au.edu.rmit.sept.webapp.models.TreatmentPlan;
import au.edu.rmit.sept.webapp.services.PetInformationService;
import au.edu.rmit.sept.webapp.services.TreatmentPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TreatmentPlanController {

    private final TreatmentPlanService treatmentPlanService;
    private final PetInformationService petInformationService;

    @Autowired
    public TreatmentPlanController(TreatmentPlanService treatmentPlanService, PetInformationService petInformationService) {
        this.treatmentPlanService = treatmentPlanService;
        this.petInformationService = petInformationService;
    }

    private void addPetSelectionToModel(Model model) {
        List<PetInformation> pets = petInformationService.getAllPets();
        model.addAttribute("pets", pets);
    }

    @GetMapping("/access-medical-records")
    public String AccessMedicalRecords(Model model) {
        addPetSelectionToModel(model);
        return "AccessMedicalRecords";
    }

    @GetMapping("/treatmentPlan")
    public String showTreatmentPlanDetails(@RequestParam("petId") Long petId, Model model) {
        PetInformation pet = petInformationService.getPetById(petId);
        if (pet != null) {
            List<TreatmentPlan> treatmentPlans = treatmentPlanService.getTreatmentPlanByPetId(petId);
            model.addAttribute("pet", pet);
            model.addAttribute("treatmentPlans", treatmentPlans);
        } else {
            model.addAttribute("errorMessage", "No pet found with the given ID.");
        }
        return "ViewTreatmentPlan";
    }
}

