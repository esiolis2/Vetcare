package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.models.PetInformation;
import au.edu.rmit.sept.webapp.models.VaccinationRecord;
import au.edu.rmit.sept.webapp.services.PetInformationService;
import au.edu.rmit.sept.webapp.services.VaccinationRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class VaccinationRecordController {

    private final PetInformationService petInformationService;
    private final VaccinationRecordService vaccinationRecordService;

    @Autowired
    public VaccinationRecordController(PetInformationService petInformationService,VaccinationRecordService vaccinationRecordService) {
        this.petInformationService = petInformationService;
        this.vaccinationRecordService = vaccinationRecordService;
    }


    private void addPetSelectionToModel(Model model) {
        List<PetInformation> pets = petInformationService.getAllPets();
        model.addAttribute("pets", pets);
    }

    @GetMapping("/view-vaccination-records")
    public String ViewVaccinationRecords(Model model){
        addPetSelectionToModel(model);
        return"ViewVaccinationRecords";
    }
//
//
//    @GetMapping("/vaccination")
//    public String showVaccinationDetails(@RequestParam("petId") Long petId, Model model) {
//        PetInformation pet = petInformationService.getPetById(petId);
//        if (pet != null) {
//            List<VaccinationRecord> vaccinationRecords = vaccinationRecordService.getVaccinationRecordByPetId(petId);
//            model.addAttribute("pet", pet);
//            model.addAttribute("vaccinationRecords", vaccinationRecords);
//        } else {
//            model.addAttribute("errorMessage", "Pet not found.");
//        }
//
//        addPetSelectionToModel(model);
//        return "ViewVaccinationRecords";
//    }

}



//    @GetMapping("/vaccinationRecord")
//    public String viewMedicalRecord(@RequestParam("petId") Long petId, Model model) {
//        PetInformation pet = petInformationService.getPetById(petId);
//        if (pet != null) {
//            List<VaccinationRecord> vaccinationRecords = vaccinationRecordService.getVaccinationRecordByPetId(petId);
//
//            model.addAttribute("pet", pet);
//            model.addAttribute("vaccinationRecords", vaccinationRecords);
//        }
//
//        addPetSelectionToModel(model);
//        return "AccessMedicalRecords";
//    }