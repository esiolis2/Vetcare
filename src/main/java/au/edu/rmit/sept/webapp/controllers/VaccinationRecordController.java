package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.models.PetInformation;
import au.edu.rmit.sept.webapp.models.User;
import au.edu.rmit.sept.webapp.models.VaccinationRecord;
import au.edu.rmit.sept.webapp.services.PetInformationService;
import au.edu.rmit.sept.webapp.services.UserService;
import au.edu.rmit.sept.webapp.services.VaccinationRecordService;
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
public class VaccinationRecordController {

    private final PetInformationService petInformationService;
    private final VaccinationRecordService vaccinationRecordService;
    private final UserService userService;

    @Autowired
    public VaccinationRecordController(PetInformationService petInformationService, VaccinationRecordService vaccinationRecordService, UserService userService) {
        this.petInformationService = petInformationService;
        this.vaccinationRecordService = vaccinationRecordService;
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

    @GetMapping("/view-vaccination-records")
    public String viewVaccinationRecordsPage(Model model, HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("userId");

        if (userId == null) {
            model.addAttribute("errorMessage", "No vaccination records available for the user.");
            return "ViewVaccinationRecords";
        }

        List<PetInformation> pets = petInformationService.getPetByUserId(userId);

        if (pets == null || pets.isEmpty()) {
            model.addAttribute("errorMessage", "No vaccination records found for the logged-in user.");
        } else {
            model.addAttribute("pets", pets);
        }

        return "ViewVaccinationRecords";
    }

    @GetMapping("/vaccination-record-details")
    public String showVaccinationDetails(@RequestParam("petId") Long petId, Model model, HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("userId");

        if (userId == null) {
            model.addAttribute("errorMessage", "You must be logged in to view vaccination records.");
            addPetSelectionToModel(model, request);
            return "ViewVaccinationRecords";
        }

        PetInformation pet = petInformationService.getPetById(petId);
        if (pet != null) {
            if (pet.getOwnerId().equals(userId)) {
                List<VaccinationRecord> vaccinationRecords = vaccinationRecordService.getVaccinationRecordByPetId(petId);

                if (vaccinationRecords != null && !vaccinationRecords.isEmpty()) {
                    model.addAttribute("pet", pet);
                    model.addAttribute("vaccinationRecords", vaccinationRecords);
                } else {
                    model.addAttribute("errorMessage", "No vaccination records found for this pet.");
                }
            } else {
                model.addAttribute("errorMessage", "You are not authorized to view this pet's vaccination records.");
            }
        } else {
            model.addAttribute("errorMessage", "Pet not found.");
        }
        addPetSelectionToModel(model, request);
        return "ViewVaccinationRecords";
    }



    @GetMapping("/edit-vaccination-record")
    public String editVaccinationRecordForm(Model model, HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        String userType = (String) request.getSession().getAttribute("userType");

        List<User> users = userService.getAllUsers().stream()
                .filter(user -> "User".equals(user.getUserType()))
                .collect(Collectors.toList());
        model.addAttribute("users", users);

        List<PetInformation> pets = petInformationService.getPetByUserId(userId);
        model.addAttribute("pets", pets);

        model.addAttribute("vaccinationRecord", new VaccinationRecord());

        model.addAttribute("canEditPet", "Vet".equals(userType));

        return "vaccinationForm";
    }


    @GetMapping("/vaccination/selectUser")
    public String selectUser(@RequestParam("userId") Long userId, Model model) {
        User selectedUser = userService.findByUser(userId);
        List<PetInformation> pets = petInformationService.getPetByUserId(userId);
        if (pets.isEmpty()) {
            model.addAttribute("errorMessage", "No pets found for this user.");
        }
        model.addAttribute("selectedUser", selectedUser);
        model.addAttribute("pets", pets);
        model.addAttribute("users", List.of(selectedUser));
        model.addAttribute("vaccinationRecord", new VaccinationRecord());

        return "vaccinationForm";
    }


    @GetMapping("/vaccination/selectPet")
    public String selectPet(@RequestParam("petId") Long petId, Model model) {
        PetInformation selectedPet = petInformationService.getPetById(petId);
        User selectedUser = userService.findByUser(selectedPet.getOwnerId());
        if (selectedPet == null) {
            model.addAttribute("errorMessage", "Pet not found.");
        }
        model.addAttribute("selectedPet", selectedPet);
        model.addAttribute("selectedUser", selectedUser);
        model.addAttribute("pets", List.of(selectedPet));
        model.addAttribute("users", List.of(selectedUser));
        model.addAttribute("vaccinationRecord", new VaccinationRecord());

        return "vaccinationForm";
    }

    @PostMapping("/save-vaccination")
    public String saveVaccination(@ModelAttribute("vaccinationRecord") VaccinationRecord vaccinationRecord,
                                  @RequestParam("petId") Long petId, HttpServletRequest request, Model model) {

        User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
        PetInformation pet = petInformationService.getPetById(petId);
        if (pet != null) {
            vaccinationRecord.setPet(pet);

            if (vaccinationRecord.getVaccinationID() == null) {
                vaccinationRecordService.createVaccinationRecord(vaccinationRecord);
            }

            model.addAttribute("successMessage", "Vaccination record saved successfully.");
        } else {
            model.addAttribute("errorMessage", "Pet not found.");
            return "vaccinationForm";
        }
        return "HomePage";
    }

    
    @ModelAttribute("loggedInUser")
    public User getLoggedInUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("loggedInUser");
    }

    //  To ensure that these attributes are available in the model for the thyemleaf template to use these attributes for conditional rendering
    @ModelAttribute("userType")
    public String getUserType(HttpServletRequest request) {
        return (String) request.getSession().getAttribute("userType");
    }
}
