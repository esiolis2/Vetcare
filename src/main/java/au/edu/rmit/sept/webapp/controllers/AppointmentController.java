package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.models.Appointment;
import au.edu.rmit.sept.webapp.models.Clinic;
import au.edu.rmit.sept.webapp.models.Veterinarian;
import au.edu.rmit.sept.webapp.services.AppointmentService;
import au.edu.rmit.sept.webapp.services.AppointmentServiceImpl;
import au.edu.rmit.sept.webapp.services.ClinicService;
import au.edu.rmit.sept.webapp.services.VeterinarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Controller
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final ClinicService clinicService;
    private final VeterinarianService veterinarianService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, ClinicService clinicService, VeterinarianService veterinarianService) {
        this.appointmentService = appointmentService;
        this.clinicService = clinicService;
        this.veterinarianService = veterinarianService;
    }

    @GetMapping("/appointments")
    public String BookApp(Model model) {
        List<Clinic> clinics = clinicService.getAllClinics();
        model.addAttribute("clinics", clinics);
        List<Veterinarian> veterinarians = veterinarianService.getAllVeterinarians();
        model.addAttribute("veterinarians", veterinarians);
        return "BookApp";
    }

    @PostMapping("/add")
    public String createAppointment(@ModelAttribute Appointment appointment){
        appointmentService.createAppointment(appointment);
        return "redirect:/";
    }


}
