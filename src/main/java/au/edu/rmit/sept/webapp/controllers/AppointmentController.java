package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.models.*;
import au.edu.rmit.sept.webapp.services.*;
import jakarta.servlet.http.HttpServletRequest;
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
    private final PetInformationService petInformationService;
    private final UserService userService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, ClinicService clinicService, VeterinarianService veterinarianService, ClinicServicePricingService clinicServicePricingService, ClinicReasonsService clinicReasonsService, PetInformationService petInformationService, UserService userService) {
        this.appointmentService = appointmentService;
        this.clinicService = clinicService;
        this.veterinarianService = veterinarianService;
        this.clinicServicePricingService = clinicServicePricingService;
        this.clinicReasonsService = clinicReasonsService;
        this.petInformationService = petInformationService;
        this.userService = userService;
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
        List<PetInformation> petInformation = petInformationService.getAllPets();
        model.addAttribute("petInformation", petInformation);

        return "BookApp";
    }

    @PostMapping("/add")
    public String createAppointment(@ModelAttribute Appointment appointment, HttpServletRequest request){
        String email = (String) request.getSession().getAttribute("userEmail");
        if (email != null) {
            User user = userService.findByEmail(email);
            appointment.setUserId(user.getId());
            appointmentService.createAppointment(appointment);
        }
        else{
            System.out.print("An error occured while booking appointment with user.");
            return "redirect:/";
        }


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

    @DeleteMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable Long id)
    {
        appointmentService.deleteAppointment(id);
        System.out.println("Successfully deleted appointment");
        return "redirect:/";
    }

}
