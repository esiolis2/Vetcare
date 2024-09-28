package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.models.PetInformation;
import au.edu.rmit.sept.webapp.models.TreatmentPlan;
import au.edu.rmit.sept.webapp.services.PetInformationService;
import au.edu.rmit.sept.webapp.services.TreatmentPlanService;
import au.edu.rmit.sept.webapp.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TreatmentPlanController {

    private final TreatmentPlanService treatmentPlanService;
    private final PetInformationService petInformationService;
    private final UserService userService;

    @Autowired
    public TreatmentPlanController(TreatmentPlanService treatmentPlanService, PetInformationService petInformationService, UserService userService) {
        this.treatmentPlanService = treatmentPlanService;
        this.petInformationService = petInformationService;
        this.userService = userService;
    }

    private void addPetSelectionToModel(Model model, HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (userId != null) {
            List<PetInformation> pets = petInformationService.getPetByUserId(userId);
            if (pets != null && !pets.isEmpty()) {
                model.addAttribute("pets", pets);
            } else {
                model.addAttribute("errorMessage", "No pets found for the logged-in user.");
            }
        } else {
            model.addAttribute("errorMessage", "You must be logged in to view your pets.");
        }
    }

    @GetMapping("/view-treatment-plan")
    public String viewTreatmentPlanPage(Model model, HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("userId");

        if (userId == null) {
            model.addAttribute("errorMessage", "No treatment plans available for the user.");
            System.out.println("No treatment plans available for the user.");
            return "ViewTreatmentPlan";
        }
        List<PetInformation> pets = petInformationService.getPetByUserId(userId);

        if (pets == null || pets.isEmpty()) {
            model.addAttribute("errorMessage", "No treatment plans found for the logged-in user.");
            System.out.println("No treatment plans found for the logged-in user.");
            return "ViewTreatmentPlan";
        } else {
            model.addAttribute("pets", pets);
        }

        return "ViewTreatmentPlan";
    }

    @GetMapping("/treatmentPlan")
    public String viewTreatmentPlan(@RequestParam("petId") Long petId, Model model, HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (userId == null) {
            model.addAttribute("errorMessage", "You must be logged in to view treatment plans.");
            addPetSelectionToModel(model, request);
            return "ViewTreatmentPlan";
        }

        PetInformation pet = petInformationService.getPetById(petId);
        if (pet != null) {
            if (pet.getOwnerId().equals(userId)) {
                List<TreatmentPlan> treatmentPlans = treatmentPlanService.getTreatmentPlanByPetId(petId);

                if (treatmentPlans != null && !treatmentPlans.isEmpty()) {
                    model.addAttribute("pet", pet);
                    model.addAttribute("treatmentPlans", treatmentPlans);
                } else {
                    model.addAttribute("errorMessage", "No treatment plans found for this pet.");
                }
            } else {
                model.addAttribute("errorMessage", "You are not authorized to view this pet's treatment plans.");
            }
        } else {
            model.addAttribute("errorMessage", "Pet not found.");
        }
        addPetSelectionToModel(model, request);
        return "ViewTreatmentPlan";
    }
}
