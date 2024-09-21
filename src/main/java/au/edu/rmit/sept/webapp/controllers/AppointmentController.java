package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.models.*;
import au.edu.rmit.sept.webapp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Controller
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final ClinicService clinicService;
    private final VeterinarianService veterinarianService;
    private final ClinicServicePricingService clinicServicePricingService;
    private final ClinicReasonsService clinicReasonsService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, ClinicService clinicService, VeterinarianService veterinarianService, ClinicServicePricingService clinicServicePricingService, ClinicReasonsService clinicReasonsService) {
        this.appointmentService = appointmentService;
        this.clinicService = clinicService;
        this.veterinarianService = veterinarianService;
        this.clinicServicePricingService = clinicServicePricingService;
        this.clinicReasonsService = clinicReasonsService;
    }

    @GetMapping("/appointments")
    public String BookApp(Model model) {
        List<Clinic> clinics = clinicService.getAllClinics();
        model.addAttribute("clinics", clinics);
        List<Veterinarian> veterinarians = veterinarianService.getAllVeterinarians();
        model.addAttribute("veterinarians", veterinarians);
        List<ClinicReasons> clinicReasons = clinicReasonsService.getAllClinicReasons();
        model.addAttribute("clinicReasons", clinicReasons);
        List<ClinicServicePricing> servicePricings = clinicServicePricingService.getAll();
        model.addAttribute("servicePricings", servicePricings);
        return "BookApp";
    }

    @PostMapping("/add")
    public String createAppointment(@ModelAttribute Appointment appointment){
        appointmentService.createAppointment(appointment);
        return "redirect:/";
    }

    @GetMapping ("/appointments/reschedule")
    public String ChangeApp(Model model){
        List<Veterinarian> veterinarians = veterinarianService.getAllVeterinarians();
        model.addAttribute("veterinarians", veterinarians);
        return "ChangeApp.html";
    }

    @PostMapping("/reschedule")
    public String rescheduleAppointment(Model model, LocalDate date, LocalTime time, Long veterinarianId, Long id ){
        Appointment appointment = appointmentService.findAppointmentById(id);
        appointment.setAppointmentTime(time);
        appointment.setAppointmentDate(date);
        appointment.setVeterinarianId(veterinarianId);
        appointmentService.rescheduleAppointment(appointment);
        System.out.println("Successfully rescheduled appointment");
        return "redirect:/";
    }


}
