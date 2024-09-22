package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.models.Clinic;
import au.edu.rmit.sept.webapp.models.ClinicReasons;
import au.edu.rmit.sept.webapp.models.ClinicServicePricing;
import au.edu.rmit.sept.webapp.models.Veterinarian;
import au.edu.rmit.sept.webapp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.List;

@Controller
public class ClinicController {

    private final ClinicService clinicService;
    private final VeterinarianService veterinarianService;
    private final ClinicServicePricingService clinicServicePricingService;
    private final ClinicReasonsService clinicReasonsService;

    @Autowired
    public ClinicController(ClinicService clinicService, VeterinarianService veterinarianService, ClinicServicePricingService clinicServicePricingService, ClinicReasonsService clinicReasonsService) {
        this.clinicService = clinicService;
        this.veterinarianService = veterinarianService;
        this.clinicServicePricingService = clinicServicePricingService;
        this.clinicReasonsService = clinicReasonsService;
    }


    @GetMapping("/clinics")
    public String Clinics(Model model) {
        List<Clinic> clinics = clinicService.getAllClinics();
        model.addAttribute("clinics", clinics);
        List<Veterinarian> veterinarians = veterinarianService.getAllVeterinarians();
        model.addAttribute("veterinarians", veterinarians);
        List<ClinicReasons> clinicReasons = clinicReasonsService.getAllClinicReasons();
        model.addAttribute("clinicReasons", clinicReasons);
        List<ClinicServicePricing> servicePricings = clinicServicePricingService.getAll();
        model.addAttribute("servicePricings", servicePricings);
        return "Clinics";
    }

}
