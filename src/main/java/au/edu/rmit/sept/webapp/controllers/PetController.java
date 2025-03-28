package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.models.PetInformation;
import au.edu.rmit.sept.webapp.models.User;
import au.edu.rmit.sept.webapp.services.PetInformationService;
import au.edu.rmit.sept.webapp.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PetController {
    private final PetInformationService petInformationService;
    private final UserService userService;
    private final PetInformationService petInfoService;

    @Autowired
    public PetController(PetInformationService petInformationService, UserService userService, PetInformationService petInfoService) {
        this.petInformationService = petInformationService;
        this.userService = userService;
        this.petInfoService = petInfoService;
    }

    @ModelAttribute("loggedInUser")
    public User getLoggedInUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("loggedInUser");
    }

    @GetMapping("/pets/register")
    public String showRegistrationForm(Model model) {
        // You can add any additional attributes to the model if needed
        return "PetRegistration"; // This should match the name of your HTML template
    }

    @PostMapping("/pets/register")
    public String registerPet(@ModelAttribute PetInformation petInformation, HttpServletRequest request) {
        String email = (String) request.getSession().getAttribute("userEmail");
        if (email != null) {
            User user = userService.findByEmail(email);
            petInformation.setOwnerId(user.getId());
            petInformationService.createPetInformation(petInformation);
            System.out.println("Pet registered successfully");
            return "redirect:/appointments"; // Adjust as necessary

        }
        return "redirect:/login";

    }

    @GetMapping("/dashboard/pet-management")
    public String petManagementDashboard(Model model, HttpServletRequest request) {
        String email = (String) request.getSession().getAttribute("userEmail");
        if (email != null) {
            List<PetInformation> petInformation = petInfoService.getPetByUserId(userService.findByEmail(email).getId());
            model.addAttribute("petInformation", petInformation);
        }
        return "/dashboard/PetManagement";
    }
}
