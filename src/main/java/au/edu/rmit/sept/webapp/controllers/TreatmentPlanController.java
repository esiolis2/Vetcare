package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.models.PetInformation;
import au.edu.rmit.sept.webapp.models.TreatmentPlan;
import au.edu.rmit.sept.webapp.models.User;
import au.edu.rmit.sept.webapp.services.PetInformationService;
import au.edu.rmit.sept.webapp.services.TreatmentPlanService;
import au.edu.rmit.sept.webapp.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/edit-treatment-plan")
    public String editTreatmentPlanForm(Model model, HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        String userType = (String) request.getSession().getAttribute("userType");

        List<User> users = userService.getAllUsers().stream()
                .filter(user -> "User".equals(user.getUserType()))
                .collect(Collectors.toList());
        model.addAttribute("users", users);

        model.addAttribute("selectedUser", null);
        model.addAttribute("pets", null);
        model.addAttribute("treatmentPlan", new TreatmentPlan());

        model.addAttribute("canEditPet", "Vet".equals(userType));

        return "treatmentForm";
    }

    @GetMapping("/treatment/selectUser")
    public String selectUser(@RequestParam("userId") Long userId, Model model) {
        User selectedUser = userService.findByUser(userId);
        List<PetInformation> pets = petInformationService.getPetByUserId(userId);

        if (pets.isEmpty()) {
            model.addAttribute("errorMessage", "No pets found for this user.");
        }

        model.addAttribute("selectedUser", selectedUser);
        model.addAttribute("pets", pets);
        model.addAttribute("users", List.of(selectedUser));
        model.addAttribute("treatmentPlan", new TreatmentPlan());

        return "treatmentForm";
    }


    @GetMapping("/treatment/selectPet")
    public String selectPet(@RequestParam("petId") Long petId, Model model) {
        PetInformation selectedPet = petInformationService.getPetById(petId);
        User selectedUser = userService.findByUser(selectedPet.getOwnerId());

        if (selectedPet == null) {
            model.addAttribute("errorMessage", "Pet not found.");
        }

        model.addAttribute("selectedPet", selectedPet);
        model.addAttribute("selectedUser", selectedUser);
//        model.addAttribute("pets", petInformationService.getPetByUserId(selectedUser.getId()));
        model.addAttribute("pets", List.of(selectedPet));
        model.addAttribute("users", List.of(selectedUser));
        model.addAttribute("treatmentPlan", new TreatmentPlan());

        return "treatmentForm";
    }

    @PostMapping("/save-treatment-plan")
    public String saveTreatmentPlan(@ModelAttribute("treatmentPlan") TreatmentPlan treatmentPlan,
                                    @RequestParam("petId") Long petId, HttpServletRequest request, Model model) {

        User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");

        PetInformation pet = petInformationService.getPetById(petId);

        if (pet != null) {
            treatmentPlan.setPet(pet);
        } else {
            model.addAttribute("errorMessage", "Pet not found.");
            return "treatmentForm";
        }

        List<TreatmentPlan> existingPlans = treatmentPlanService.getTreatmentPlanByPetId(petId);

        if (existingPlans == null || existingPlans.isEmpty()) {
            treatmentPlanService.createTreatmentPlan(treatmentPlan);
            model.addAttribute("successMessage", "New treatment plan created successfully.");
        }

//        else {
//            TreatmentPlan existingPlan = existingPlans.get(0);
//            treatmentPlan.setTreatmentPlanID(existingPlan.getTreatmentPlanID());
//            treatmentPlanService.updateTreatmentPlan(treatmentPlan, loggedInUser);
//            model.addAttribute("successMessage", "Treatment plan updated successfully.");
//        }

        return "HomePage";
    }

    @ModelAttribute("loggedInUser")
    public User getLoggedInUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("loggedInUser");
    }

    @ModelAttribute("userType")
    public String getUserType(HttpServletRequest request) {
        return (String) request.getSession().getAttribute("userType");
    }
}
