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
    private final UserService userService;

    @Autowired
    public MedicalRecordController(PetInformationService petInformationService, MedicalHistoryService medicalHistoryService, VaccinationRecordService vaccinationRecordService, TreatmentPlanService treatmentPlanService, UserService userService){// userService userService) {
        this.petInformationService = petInformationService;
        this.medicalHistoryService = medicalHistoryService;
        this.vaccinationRecordService = vaccinationRecordService;
        this.treatmentPlanService = treatmentPlanService;
        this.userService = userService;

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

            model.addAttribute("pet", pet);
        } else {
            model.addAttribute("errorMessage", "Pet not found.");
        }
        addPetSelectionToModel(model, request);
        return "AccessMedicalRecords";
    }


    @GetMapping("/medicalHistory")
    public String showFullMedicalRecords(@RequestParam("petId") Long petId, Model model, HttpServletRequest request) {
        PetInformation pet = petInformationService.getPetById(petId);
        if (pet != null) {
            List<MedicalHistory> fullMedicalHistory = medicalHistoryService.getMedicalHistoryByPetId(petId);
            if (fullMedicalHistory.isEmpty()) {
                model.addAttribute("errorMessage", "No medical history found for the selected pet.");
            } else {
                model.addAttribute("medicalHistory", fullMedicalHistory);
            }
            model.addAttribute("pet", pet);
        } else {
            model.addAttribute("errorMessage", "Pet not found.");
        }

        addPetSelectionToModel(model, request);
        return "FullMedicalRecords";
    }

    @GetMapping("/vaccination")
    public String showVaccinationDetails(@RequestParam("petId") Long petId, Model model, HttpServletRequest request) {
        PetInformation pet = petInformationService.getPetById(petId);
        if (pet != null) {
            List<VaccinationRecord> vaccinationRecords = vaccinationRecordService.getVaccinationRecordByPetId(petId);
            if (vaccinationRecords.isEmpty()) {
                model.addAttribute("errorMessage", "No vaccination records found for the selected pet.");
            } else {
                model.addAttribute("vaccinationRecords", vaccinationRecords);
            }
            model.addAttribute("pet", pet);
        } else {
            model.addAttribute("errorMessage", "Pet not found.");
        }

        addPetSelectionToModel(model, request);
        return "ViewVaccinationRecords";
    }

    @GetMapping("/vtreatmentPlan")
    public String showTreatmentPlanDetails(@RequestParam("petId") Long petId, Model model, HttpServletRequest request) {
        PetInformation pet = petInformationService.getPetById(petId);
        if (pet != null) {
            List<TreatmentPlan> treatmentPlans = treatmentPlanService.getTreatmentPlanByPetId(petId);
            if (treatmentPlans.isEmpty()) {
                model.addAttribute("errorMessage", "No treatment plans found for the selected pet.");
            } else {
                model.addAttribute("treatmentPlans", treatmentPlans);
            }
            model.addAttribute("pet", pet);
        } else {
            model.addAttribute("errorMessage", "Pet not found.");
        }

        addPetSelectionToModel(model, request);
        return "ViewTreatmentPlan";
    }
//    @GetMapping("/edit-medical-record")
//    public String editMedicalRecordForm(Model model, HttpServletRequest request) {
//        Long userId = (Long) request.getSession().getAttribute("userId");
//        String userType = (String) request.getSession().getAttribute("userType");
//
//        List<User> users = userService.getAllUsers().stream()
//                .filter(user -> "User".equals(user.getUserType()))
//                .collect(Collectors.toList());
//        model.addAttribute("users", users);
//
//        List<PetInformation> pets = petInformationService.getPetByUserId(userId);
//        model.addAttribute("pets", pets);
//
//        model.addAttribute("medicalRecord", new MedicalHistory());
//
//        model.addAttribute("canEditPet", "Vet".equals(userType));
//
//        return "medicalRecordForm";
//    }

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


    @PostMapping("/save-medical-record")
    public String saveMedicalRecord(@ModelAttribute("medicalRecord") MedicalHistory medicalHistory,
                                    @RequestParam("petId") Long petId, HttpServletRequest request, Model model) {
        User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");

        PetInformation pet = petInformationService.getPetById(petId);

        if (pet != null) {
            medicalHistory.setPetID(petId);
            medicalHistory.setPet(pet);
        } else {
            model.addAttribute("errorMessage", "Pet not found.");
            return "medicalRecordForm";
        }

        List<MedicalHistory> existingRecords = medicalHistoryService.getMedicalHistoryByPetId(petId);

        if (existingRecords == null || existingRecords.isEmpty()) {

            medicalHistoryService.createMedicalHistory(medicalHistory);
            model.addAttribute("successMessage", "New medical record created successfully.");
        } else {

            MedicalHistory existingRecord = existingRecords.get(0);
            if (existingRecord != null && existingRecord.getHistoryID() != null) {
                medicalHistory.setHistoryID(existingRecord.getHistoryID());
                medicalHistoryService.updateMedicalHistory(medicalHistory, loggedInUser);
                model.addAttribute("successMessage", "Medical record updated successfully.");
            } else {
                model.addAttribute("errorMessage", "Error updating medical record: Record not found or History ID is null.");
                return "medicalRecordForm";
            }
        }

        return "HomePage";
    }



    @GetMapping("/medical/selectUser")
    public String selectUserForMedicalRecord(@RequestParam("userId") Long userId, Model model) {
        List<PetInformation> pets = petInformationService.getPetByUserId(userId);
        if (pets.isEmpty()) {
            model.addAttribute("errorMessage", "No pets found for this user.");
        }
        model.addAttribute("pets", pets);
        model.addAttribute("users", userService.getAllUsers());
        return "medicalRecordForm";
    }

    @GetMapping("/medical/selectPet")
    public String selectPetForMedicalRecord(@RequestParam("petId") Long petId, Model model) {
        PetInformation selectedPet = petInformationService.getPetById(petId);
        if (selectedPet == null) {
            model.addAttribute("errorMessage", "Pet not found.");
        } else {
            User user = userService.findByUser(selectedPet.getOwnerId());
            model.addAttribute("user", user);
            model.addAttribute("selectedPet", selectedPet);
        }

        model.addAttribute("medicalRecord", new MedicalHistory());
        model.addAttribute("pets", petInformationService.getPetByUserId(selectedPet.getOwnerId()));
        model.addAttribute("users", userService.getAllUsers());
        return "medicalRecordForm";
    }

//    @PostMapping("/save-medical-history")
//    public String saveMedicalHistory(@ModelAttribute("medicalHistory") MedicalHistory medicalHistory,
//                                     @RequestParam("petId") Long petId, Model model) {
//        PetInformation pet = petInformationService.getPetById(petId);
//
//        if (pet != null) {
//            medicalHistory.setPet(pet);
//            medicalHistoryService.createMedicalHistory(medicalHistory);
//            model.addAttribute("successMessage", "Medical history record saved successfully.");
//        } else {
//            model.addAttribute("errorMessage", "Pet not found.");
//        }
//        return "medicalHistoryForm";
//    }
//
//

//    @GetMapping("/add-treatment-plan")
//    public String showTreatmentPlanForm(@RequestParam("petId") Long petId, Model model) {
//        PetInformation pet = petInformationService.getPetById(petId);
//        model.addAttribute("selectedPet", pet);
//        model.addAttribute("treatmentPlan", new TreatmentPlan());
//        model.addAttribute("showTreatmentForm", true);
//        return "medicalRecordSummary";
//    }
//
//    @GetMapping("/add-vaccination-record")
//    public String showVaccinationRecordForm(@RequestParam("petId") Long petId, Model model) {
//        PetInformation pet = petInformationService.getPetById(petId);
//        model.addAttribute("selectedPet", pet);
//        model.addAttribute("vaccinationRecord", new VaccinationRecord());
//        model.addAttribute("showVaccinationForm", true);
//        return "medicalRecordSummary";
//    }


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




