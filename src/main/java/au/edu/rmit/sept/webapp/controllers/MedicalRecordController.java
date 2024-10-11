package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.models.*;
import au.edu.rmit.sept.webapp.services.*;
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
public class MedicalRecordController {

    private final PetInformationService petInformationService;
    private final MedicalHistoryService medicalHistoryService;
    private final VaccinationRecordService vaccinationRecordService;
    private final TreatmentPlanService treatmentPlanService;
    private final PrescriptionService prescriptionService;
    private final UserService userService;

    @Autowired
    public MedicalRecordController(PetInformationService petInformationService, MedicalHistoryService medicalHistoryService, VaccinationRecordService vaccinationRecordService, TreatmentPlanService treatmentPlanService, UserService userService, PrescriptionService prescriptionService){// userService userService) {
        this.petInformationService = petInformationService;
        this.medicalHistoryService = medicalHistoryService;
        this.vaccinationRecordService = vaccinationRecordService;
        this.treatmentPlanService = treatmentPlanService;
        this.userService = userService;
        this.prescriptionService = prescriptionService;
    }

    private void addPetSelectionToModel(Model model, HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (userId != null) {
            List<PetInformation> pets = petInformationService.getPetByUserId(userId);
            model.addAttribute("pets", pets);
        } else {
            model.addAttribute("errorMessage", "No pets found for the logged-in user.");
        }
    }

    @GetMapping("/access-medical-records")
    public String AccessMedicalRecords(Model model, HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("userId");

        if (userId == null) {
            model.addAttribute("errorMessage", "No medical records available for the user.");
            System.out.println("No medical records available for the user");
            return "AccessMedicalRecords";
        }

        List<PetInformation> pets = petInformationService.getPetByUserId(userId);

        if (pets == null || pets.isEmpty()) {
            model.addAttribute("errorMessage", "No medical records found for the logged-in user.");
            System.out.println("No medical records found for the logged-in user.");
            return "AccessMedicalRecords";
        } else {
            model.addAttribute("pets", pets);
        }

        return "AccessMedicalRecords";
    }

    @GetMapping("/viewMedicalRecord")
    public String viewMedicalRecord(@RequestParam("petId") Long petId, Model model, HttpServletRequest request) {
        PetInformation pet = petInformationService.getPetById(petId);
        if (pet != null) {
            User owner = userService.findByUser(pet.getOwnerId());
            model.addAttribute("owner", owner);

            List<MedicalHistory> fullMedicalHistory = medicalHistoryService.getMedicalHistoryByPetId(petId);
            List<VaccinationRecord> vaccinationRecords = vaccinationRecordService.getVaccinationRecordByPetId(petId);
            List<TreatmentPlan> treatmentPlans = treatmentPlanService.getTreatmentPlanByPetId(petId);
            List<Prescription> prescriptions = prescriptionService.getPrescriptionsByPetId(petId);

            if (fullMedicalHistory == null || fullMedicalHistory.isEmpty()) {
                model.addAttribute("medicalHistoryMessage", "No medical history found for this pet.");
            } else {
                model.addAttribute("medicalHistory", fullMedicalHistory);
            }

            if (vaccinationRecords == null || vaccinationRecords.isEmpty()) {
                model.addAttribute("vaccinationMessage", "No vaccination records available.");
            } else {
                model.addAttribute("vaccinationRecords", vaccinationRecords);
            }

            if (treatmentPlans == null || treatmentPlans.isEmpty()) {
                model.addAttribute("treatmentPlanMessage", "No treatment plans available.");
            } else {
                model.addAttribute("treatmentPlans", treatmentPlans);
            }

            if (prescriptions == null || prescriptions.isEmpty()) {
                model.addAttribute("prescriptionMessage", "No prescriptions found for the selected pet.");
            } else {
                model.addAttribute("prescriptions", prescriptions);
            }

            model.addAttribute("pet", pet);
        } else {
            model.addAttribute("errorMessage", "Pet not found.");
        }
        addPetSelectionToModel(model, request);
        return "AccessMedicalRecords";
    }


    @GetMapping("/edit-medical-record")
    public String editMedicalRecordForm(Model model, HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        User user = userService.findByUser(userId);
        if (user != null) {
            model.addAttribute("user", user);
        } else {
            model.addAttribute("errorMessage", "User not found.");
        }

        String userType = (String) request.getSession().getAttribute("userType");

        List<User> users = userService.getAllUsers().stream()
                .filter(u -> "User".equals(u.getUserType()))
                .collect(Collectors.toList());
        model.addAttribute("users", users);

        List<PetInformation> pets = petInformationService.getPetByUserId(userId);
        model.addAttribute("pets", pets);

        model.addAttribute("medicalRecord", new MedicalHistory());
        model.addAttribute("canEditPet", "Vet".equals(userType));

        return "medicalRecordForm";
    }


    @GetMapping("/medical/selectUser")
    public String selectUserForMedicalRecord(@RequestParam("userId") Long userId, Model model) {
        User selectedUser = userService.findByUser(userId);
        List<PetInformation> pets = petInformationService.getPetByUserId(userId);

        if (pets.isEmpty()) {
            model.addAttribute("errorMessage", "No pets found for this user.");
        }

        model.addAttribute("selectedUser", selectedUser);
        model.addAttribute("pets", pets);
        model.addAttribute("users", List.of(selectedUser));
        model.addAttribute("medicalRecord", new MedicalHistory());

        return "medicalRecordForm";
    }

    @GetMapping("/medical/selectPet")
    public String selectPetForMedicalRecord(@RequestParam("petId") Long petId, Model model) {
        PetInformation selectedPet = petInformationService.getPetById(petId);
        User selectedUser = userService.findByUser(selectedPet.getOwnerId());

        if (selectedPet == null) {
            model.addAttribute("errorMessage", "Pet not found.");
        }

        model.addAttribute("selectedPet", selectedPet);
        model.addAttribute("selectedUser", selectedUser);
        model.addAttribute("pets", List.of(selectedPet));
        model.addAttribute("users", List.of(selectedUser));
        model.addAttribute("medicalRecord", new MedicalHistory());

        return "medicalRecordForm";
    }



    @PostMapping("/save-medical-record")
    public String saveMedicalRecord(@ModelAttribute("medicalRecord") MedicalHistory medicalHistory,
                                    @RequestParam("petId") Long petId, HttpServletRequest request, Model model) {
        User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");

        PetInformation pet = petInformationService.getPetById(petId);
        if (pet == null) {
            model.addAttribute("errorMessage", "Pet not found.");
            return "medicalRecordForm";
        }

        medicalHistory.setPet(pet);
        medicalHistory.setPetID(petId);

        List<MedicalHistory> existingRecords = medicalHistoryService.getMedicalHistoryByPetId(petId);

        if (existingRecords == null || existingRecords.isEmpty()) {
            medicalHistoryService.createMedicalHistory(medicalHistory);
            model.addAttribute("successMessage", "New medical record created successfully.");
        }else {
            MedicalHistory existingRecord = existingRecords.get(0);

            if (existingRecord != null && existingRecord.getHistoryID() != null) {
                System.out.println("Debug: Existing record's History ID is " + existingRecord.getHistoryID());

                medicalHistory.setHistoryID(existingRecord.getHistoryID());

                medicalHistoryService.updateMedicalHistory(medicalHistory, loggedInUser);
                model.addAttribute("successMessage", "Medical record updated successfully.");
            } else {

                model.addAttribute("errorMessage", "Error updating medical record: Record not found or History ID is null.");
                System.out.println("Debug: Error - History ID is null or existing record is not valid for update.");
                return "medicalRecordForm";
            }
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




