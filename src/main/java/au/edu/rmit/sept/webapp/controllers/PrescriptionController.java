package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.models.PetInformation;
import au.edu.rmit.sept.webapp.models.Prescription;
import au.edu.rmit.sept.webapp.services.PetInformationService;
import au.edu.rmit.sept.webapp.services.PrescriptionService;
import au.edu.rmit.sept.webapp.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PrescriptionController {

    private final PrescriptionService prescriptionService;
    private final PetInformationService petInformationService;
    private final UserService userService;

    @Autowired
    public PrescriptionController(PrescriptionService prescriptionService, PetInformationService petInformationService, UserService userService) {
        this.prescriptionService = prescriptionService;
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

    @GetMapping("/prescription-management")
    public String viewPrescriptionPage(Model model, HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("userId");

        if (userId == null) {
            model.addAttribute("errorMessage", "No prescriptions available for the user.");
            System.out.println("No prescriptions available for the user.");
            return "prescription";
        }
        List<PetInformation> pets = petInformationService.getPetByUserId(userId);

        if (pets == null || pets.isEmpty()) {
            model.addAttribute("errorMessage", "No prescriptions found for the logged-in user.");
            System.out.println("No prescriptions found for the logged-in user.");
            return "prescription";
        } else {
            model.addAttribute("pets", pets);
        }

        return "prescription";
    }

    @GetMapping("/prescription")
    public String viewPrescription(@RequestParam("petId") Long petId, Model model, HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (userId == null) {
            model.addAttribute("errorMessage", "You must be logged in to view prescriptions.");
            addPetSelectionToModel(model, request);
            return "prescription";
        }

        PetInformation pet = petInformationService.getPetById(petId);
        if (pet != null) {
            if (pet.getOwnerId().equals(userId)) {
                List<Prescription> prescriptions = prescriptionService.getPrescriptionsByPetId(petId);

                if (prescriptions != null && !prescriptions.isEmpty()) {
                    model.addAttribute("pet", pet);
                    model.addAttribute("prescriptions", prescriptions);
                } else {
                    model.addAttribute("errorMessage", "No prescriptions found for this pet.");
                }
            } else {
                model.addAttribute("errorMessage", "You are not authorized to view this pet's prescriptions.");
            }
        } else {
            model.addAttribute("errorMessage", "Pet not found.");
        }
        addPetSelectionToModel(model, request);
        return "prescription";
    }
}
