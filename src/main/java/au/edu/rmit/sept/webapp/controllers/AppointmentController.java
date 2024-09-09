package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.models.Appointment;
import au.edu.rmit.sept.webapp.services.AppointmentService;
import au.edu.rmit.sept.webapp.services.AppointmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping()
    public String listAppointments(Model model) {
        Collection<Appointment> appointments = appointmentService.getAppointments();
        model.addAttribute("appointments", appointments);
        return "appointments/list";
    }

    @PostMapping("/add")
    @ResponseBody
    public String createNewAppointment(@RequestBody Appointment appointment) {
         appointmentService.createAppointment(appointment);
         return "Successfuly added user";
    }

}
