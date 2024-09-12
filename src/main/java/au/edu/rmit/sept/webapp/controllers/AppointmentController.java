package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.models.Appointment;
import au.edu.rmit.sept.webapp.services.AppointmentService;
import au.edu.rmit.sept.webapp.services.AppointmentServiceImpl;
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

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/appointments")
    public String BookApp() {
        return "BookApp";
    }

    @PostMapping("/add")
    public String createAppointment(@ModelAttribute Appointment appointment){
        appointmentService.createAppointment(appointment);
        return "redirect:/";
    }

//    @GetMapping()
//    public String listAppointments(Model model) {
//        Collection<Appointment> appointments = appointmentService.getAppointments();
//        model.addAttribute("appointments", appointments);
//        return "appointments/list";
//    }

//    @PostMapping("/add")
//    @ResponseBody
//    public String createNewAppointment(@RequestBody Appointment appointment, Model model) {
//         appointmentService.createAppointment(appointment);
//         return "Successfully added appointment";
//    }

}
