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


    @GetMapping("/view-treatment-plan")
    public String ViewFullTreatmentPlan(Model model) {
        addPetSelectionToModel(model);
        return "ViewTreatmentPlan";
    }

    @GetMapping("/treatmentPlan")
    public String viewTreatmentPlan(@RequestParam("petId") Long petId, Model model) {
        PetInformation pet = petInformationService.getPetById(petId);
        if (pet != null) {
            List<TreatmentPlan> treatmentPlans = treatmentPlanService.getTreatmentPlanByPetId(petId);

            if (treatmentPlans != null && !treatmentPlans.isEmpty()) {
                model.addAttribute("pet", pet);
                model.addAttribute("treatmentPlans", treatmentPlans);
            } else {
                model.addAttribute("errorMessage", "No treatment plans found for this pet.");
            }
        } else {
            model.addAttribute("errorMessage", "Pet not found.");
        }
        addPetSelectionToModel(model);
        return "ViewTreatmentPlan";
    }


}