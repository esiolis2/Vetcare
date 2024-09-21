package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.models.MedicalHistory;
import au.edu.rmit.sept.webapp.models.PetInformation;
import au.edu.rmit.sept.webapp.models.TreatmentPlan;
import au.edu.rmit.sept.webapp.models.VaccinationRecord;
import au.edu.rmit.sept.webapp.services.MedicalHistoryService;
import au.edu.rmit.sept.webapp.services.PetInformationService;
import au.edu.rmit.sept.webapp.services.TreatmentPlanService;
import au.edu.rmit.sept.webapp.services.VaccinationRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MedicalRecordController {

    private final PetInformationService petInformationService;
    private final MedicalHistoryService medicalHistoryService;
    private final VaccinationRecordService vaccinationRecordService;
    private final TreatmentPlanService treatmentPlanService;

    @Autowired
    public MedicalRecordController(PetInformationService petInformationService, MedicalHistoryService medicalHistoryService, VaccinationRecordService vaccinationRecordService, TreatmentPlanService treatmentPlanService) {
        this.petInformationService = petInformationService;
        this.medicalHistoryService = medicalHistoryService;
        this.vaccinationRecordService = vaccinationRecordService;
        this.treatmentPlanService = treatmentPlanService;
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

    @GetMapping("/viewMedicalRecord")
    public String viewMedicalRecord(@RequestParam("petId") Long petId, Model model) {
        PetInformation pet = petInformationService.getPetById(petId);
        if (pet != null) {
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
            model.addAttribute("errorMessage", "Pet not found.");
        }

        addPetSelectionToModel(model);
        return "AccessMedicalRecords";
    }

    @GetMapping("/medicalHistory")
    public String showFullMedicalRecords(@RequestParam("petId") Long petId, Model model) {
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

        addPetSelectionToModel(model);
        return "FullMedicalRecords";
    }

    @GetMapping("/vaccination")
    public String showVaccinationDetails(@RequestParam("petId") Long petId, Model model) {
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

        addPetSelectionToModel(model);
        return "ViewVaccinationRecords";
    }

    @GetMapping("/vtreatmentPlan")
    public String showTreatmentPlanDetails(@RequestParam("petId") Long petId, Model model) {
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

        addPetSelectionToModel(model);
        return "ViewTreatmentPlan";
    }
}














//    @GetMapping("/medicalHistoryDetails")
//    public String showFullMedicalRecords() {
//        return "FullMedicalRecords";
//    }



//@RequestParam("petId") Long petId, Model model
//        PetInformation pet = petInformationService.getPetById(petId);
//        if (pet != null) {
//            List<TreatmentPlan> treatmentPlans = treatmentPlanService.getTreatmentPlanByPetId(petId);
//            model.addAttribute("pet", pet);
//            model.addAttribute("treatmentPlans", treatmentPlans);
//        } else {
//            model.addAttribute("errorMessage", "No treatment plans found for this pet.");
//        }
//        addPetSelectionToModel(model);

//package au.edu.rmit.sept.webapp.controllers;
//
//import au.edu.rmit.sept.webapp.models.MedicalHistory;
//import au.edu.rmit.sept.webapp.models.PetInformation;
//import au.edu.rmit.sept.webapp.models.TreatmentPlan;
//import au.edu.rmit.sept.webapp.models.VaccinationRecord;
//import au.edu.rmit.sept.webapp.services.MedicalHistoryService;
//import au.edu.rmit.sept.webapp.services.PetInformationService;
//import au.edu.rmit.sept.webapp.services.TreatmentPlanService;
//import au.edu.rmit.sept.webapp.services.VaccinationRecordService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Collection;
//import java.util.List;
//
//@Controller
//public class MedicalRecordController {
//
//    private final PetInformationService petInformationService;
//    private final MedicalHistoryService medicalHistoryService;
//    private final VaccinationRecordService vaccinationRecordService;
//    private final TreatmentPlanService treatmentPlanService;
//
//
//    @Autowired
//    public MedicalRecordController(PetInformationService petInformationService, MedicalHistoryService medicalHistoryService, VaccinationRecordService vaccinationRecordService, TreatmentPlanService treatmentPlanService) {
//        this.petInformationService = petInformationService;
//        this.medicalHistoryService = medicalHistoryService;
//        this.vaccinationRecordService = vaccinationRecordService;
//        this.treatmentPlanService = treatmentPlanService;
//    }
//
//    @GetMapping("/access-medical-records")
//    public String AccessMedicalRecords(Model model) {
//        List<PetInformation> pets = petInformationService.getAllPets();
//        model.addAttribute("pets", pets);
//        return "AccessMedicalRecords";
//    }
//
//    @GetMapping("/viewMedicalRecord")
//    public String viewMedicalRecord(@RequestParam("petId") Long petId, Model model) {
//        PetInformation pet = petInformationService.getPetById(petId);
//        if (pet != null) {
//            List<MedicalHistory> fullMedicalHistory = medicalHistoryService.getMedicalHistoryByPetId(petId);
//            List<VaccinationRecord> vaccinationRecords = vaccinationRecordService.getVaccinationRecordByPetId(petId);
//            List<TreatmentPlan> treatmentPlans = treatmentPlanService.getTreatmentPlanByPetId(petId);
//
//            model.addAttribute("pet", pet);
//            model.addAttribute("medicalHistory", fullMedicalHistory);
//            model.addAttribute("vaccinationRecords", vaccinationRecords);
//            model.addAttribute("treatmentPlans", treatmentPlans);  // Updated treatment plan attribute
//        }
//        return "AccessMedicalRecords";
//    }
//    @GetMapping("/medicalHistoryDetails")
//    public String showFullMedicalRecords() {
//        return "FullMedicalRecords";
//    }
//
//    @GetMapping("/vaccinationDetails")
//    public String showVaccinationDetails() {
//        return "ViewVaccinationRecords";
//    }
//
//    @GetMapping("/treatmentPlanDetails")
//    public String showTreatmentPlanDetails() {
//        return "ViewTreatmentPlan";
//    }
//}






















//    @GetMapping("/vaccinationDetails")
//    public String showVaccinationDetails(@RequestParam("petId") Long petId, Model model) {
//        PetInformation pet = petInformationService.getPetById(petId);
//        if (pet != null) {
//            List<VaccinationRecord> vaccinationRecords = vaccinationRecordService.getVaccinationRecordByPetId(petId);
//            model.addAttribute("pet", pet);
//            model.addAttribute("vaccinationRecords", vaccinationRecords);
//        }
//        return "ViewVaccinationRecords";
//    }

