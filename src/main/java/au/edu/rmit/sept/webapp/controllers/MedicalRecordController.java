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

            model.addAttribute("pet", pet);
            model.addAttribute("medicalHistory", fullMedicalHistory);
            model.addAttribute("vaccinationRecords", vaccinationRecords);
            model.addAttribute("treatmentPlans", treatmentPlans);
        }

        addPetSelectionToModel(model);
        return "AccessMedicalRecords";
    }

    @GetMapping("/medicalHistory")
    public String showFullMedicalRecords(@RequestParam("petId") Long petId, Model model) {
        PetInformation pet = petInformationService.getPetById(petId);
        if (pet != null) {
            List<MedicalHistory> fullMedicalHistory = medicalHistoryService.getMedicalHistoryByPetId(petId);
            model.addAttribute("pet", pet);
            model.addAttribute("medicalHistory", fullMedicalHistory);
        }

        addPetSelectionToModel(model);
        return "FullMedicalRecords";
    }

    @GetMapping("/vaccination")
    public String showVaccinationDetails(@RequestParam("petId") Long petId, Model model) {
        PetInformation pet = petInformationService.getPetById(petId);
        if (pet != null) {
            List<VaccinationRecord> vaccinationRecords = vaccinationRecordService.getVaccinationRecordByPetId(petId);
            model.addAttribute("pet", pet);
            model.addAttribute("vaccinationRecords", vaccinationRecords);
        }

        addPetSelectionToModel(model);
        return "ViewVaccinationRecords";
    }

    @GetMapping("/treatmentPlan")
    public String showTreatmentPlanDetails(@RequestParam("petId") Long petId, Model model) {
        PetInformation pet = petInformationService.getPetById(petId);
        if (pet != null) {
            List<TreatmentPlan> treatmentPlans = treatmentPlanService.getTreatmentPlanByPetId(petId);
            model.addAttribute("pet", pet);
            model.addAttribute("treatmentPlans", treatmentPlans);
        }

        addPetSelectionToModel(model);
        return "ViewTreatmentPlan";
    }

    @GetMapping("/medicalHistoryDetails")
    public String showFullMedicalRecords() {
        return "FullMedicalRecords";
    }

    @GetMapping("/vaccinationDetails")
    public String showVaccinationDetails() {
        return "ViewVaccinationRecords";
    }

    @GetMapping("/treatmentPlanDetails")
    public String showTreatmentPlanDetails() {
        return "ViewTreatmentPlan";
    }
}








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

