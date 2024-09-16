package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.models.MedicalHistory;
import au.edu.rmit.sept.webapp.models.PetInformation;
import au.edu.rmit.sept.webapp.services.MedicalHistoryService;
import au.edu.rmit.sept.webapp.services.PetInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Controller
public class MedicalRecordController {

    private final PetInformationService petInformationService;
    private final MedicalHistoryService medicalHistoryService;

    @Autowired
    public MedicalRecordController(PetInformationService petInformationService, MedicalHistoryService medicalHistoryService) {
        this.petInformationService = petInformationService;
        this.medicalHistoryService = medicalHistoryService;
    }

    @GetMapping("/access-medical-records")
    public String AccessMedicalRecords(Model model) {
        List<PetInformation> pets = petInformationService.getAllPets();
        model.addAttribute("pets", pets);
        return "AccessMedicalRecords";
    }

    @GetMapping("/viewMedicalRecord")
    public String viewMedicalRecord(@RequestParam("petId") Long petId, Model model) {
        PetInformation pet = petInformationService.getPetById(petId);
        if (pet != null) {
            List<MedicalHistory> fullMedicalHistory = medicalHistoryService.getMedicalHistoryByPetId(petId);
            model.addAttribute("pet", pet);
            model.addAttribute("medicalHistory", fullMedicalHistory);
        }
        return "AccessMedicalRecords";
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
