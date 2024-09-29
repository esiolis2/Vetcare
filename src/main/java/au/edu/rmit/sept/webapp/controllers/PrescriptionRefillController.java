package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.models.PrescriptionRefillRequest;
import au.edu.rmit.sept.webapp.services.PrescriptionRefillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PrescriptionRefillController {

    private final PrescriptionRefillService prescriptionRefillService;

    @Autowired
    public PrescriptionRefillController(PrescriptionRefillService prescriptionRefillService) {
        this.prescriptionRefillService = prescriptionRefillService;
    }

    @PostMapping("/prescription/refill")
    public String requestRefill(@RequestParam Long petId,
                                @RequestParam List<String> medications,
                                @RequestParam String address,
                                @RequestParam String city,
                                @RequestParam String state,
                                @RequestParam String postcode,
                                @RequestParam(required = false) String notes,
                                Model model) {

        PrescriptionRefillRequest refillRequest = new PrescriptionRefillRequest(
                petId,
                String.join(", ", medications),
                address,
                city,
                state,
                postcode,
                notes
        );

        prescriptionRefillService.saveRefillRequest(refillRequest);

        model.addAttribute("successMessage", "Your prescription refill request has been submitted successfully.");

        return "prescription";
    }
}

