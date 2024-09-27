package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.models.*;
import au.edu.rmit.sept.webapp.services.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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

            if (owner.getName().equals("Mark Smith")) {
                model.addAttribute("owner", owner);

                List<MedicalHistory> fullMedicalHistory = medicalHistoryService.getMedicalHistoryByPetId(petId);
                List<VaccinationRecord> vaccinationRecords = vaccinationRecordService.getVaccinationRecordByPetId(petId);
                List<TreatmentPlan> treatmentPlans = treatmentPlanService.getTreatmentPlanByPetId(petId);

                if (fullMedicalHistory.isEmpty() && vaccinationRecords.isEmpty() && treatmentPlans.isEmpty()) {
                    model.addAttribute("errorMessage", "No medical records found for the selected pet.");
                } else {
                    model.addAttribute("medicalHistory", fullMedicalHistory);
                    model.addAttribute("vaccinationRecords", vaccinationRecords);
                    model.addAttribute("treatmentPlans", treatmentPlans);
                }

                model.addAttribute("pet", pet);
            } else {
                model.addAttribute("errorMessage", "No medical records available for the user.");
            }
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
}




