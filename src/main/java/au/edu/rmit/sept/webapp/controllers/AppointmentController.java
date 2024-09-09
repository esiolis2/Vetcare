package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.models.Appointment;
import au.edu.rmit.sept.webapp.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService service;

    @Autowired
    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    @GetMapping("/book")
    public String showBookingForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        return "appointments/book";
    }

    @PostMapping("/book")
    public String bookAppointment(Appointment appointment) {
        service.bookAppointment(appointment);
        return "redirect:/appointments/list";
    }
}




















//package au.edu.rmit.sept.webapp.controllers;
//
//import au.edu.rmit.sept.webapp.models.Appointment;
//import au.edu.rmit.sept.webapp.services.AppointmentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.Collection;
//import java.util.ArrayList;
//@Controller
//@RequestMapping("/appointments")
//public class AppointmentController {
//
//    private final AppointmentService service;
//
//    @Autowired
//    public AppointmentController(AppointmentService service) {
//        this.service = service;
//    }
//
//    @GetMapping("/book")
//    public String showBookingForm(Model model) {
//        model.addAttribute("appointment", new Appointment(null, null, null, null, null, null, null, null, null));
//        return "appointments/book";
//    }
//
//    @PostMapping("/book")
//    public String bookAppointment(Appointment appointment) {
//        service.bookAppointment(appointment);
//        return "redirect:/appointments/list";
//    }
//}




















//import java.util.Collection;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import au.edu.rmit.sept.webapp.models.Appointment;
//import au.edu.rmit.sept.webapp.services.AppointmentService;
//
//@Controller
//@RequestMapping("/appointments")
//public class AppointmentController {
//
//    private final AppointmentService service;
//
//    @Autowired
//    public AppointmentController(AppointmentService service) {
//        this.service = service;
//    }
//
//    @GetMapping
//    public String listAppointments(Model model) {
//        Collection<Appointment> appointments = service.getAppointments();
//        model.addAttribute("appointments", appointments);
//        return "appointments/list";
//    }
//
//    @GetMapping("/book")
//    public String showBookingForm(Model model) {
//        model.addAttribute("appointment", new Appointment(null, null, null, null, null, null, null, null, null));
//        return "appointments/book";
//    }
//
//    @PostMapping("/book")
//    public String bookAppointment(Appointment appointment) {
//        service.bookAppointment(appointment);
//        return "redirect:/appointments";
//    }
//}



















//package au.edu.rmit.sept.webapp.controllers;
//
//import au.edu.rmit.sept.webapp.models.Appointment;
//import au.edu.rmit.sept.webapp.services.AppointmentService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class AppointmentController {
//
//    private final AppointmentService service;
//
//    public AppointmentController(AppointmentService service) {
//        this.service = service;
//    }
//
//    @GetMapping("/appointments")
//    public String viewAppointments(Model model) {
//        model.addAttribute("appointments", service.getAllAppointments());
//        return "appointments/list";
//    }
//
//    @GetMapping("/appointments/book")
//    public String bookAppointmentForm(Model model) {
//        model.addAttribute("appointment", new Appointment());
//        return "appointments/book";
//    }
//
//    @PostMapping("/appointments/book")
//    public String bookAppointment(@ModelAttribute Appointment appointment) {
//        service.bookAppointment(appointment);
//        return "redirect:/appointments";
//    }
//}













//package au.edu.rmit.sept.webapp.controllers;
//
//import au.edu.rmit.sept.webapp.models.Appointment;
//import au.edu.rmit.sept.webapp.services.AppointmentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/appointments")
//public class AppointmentController {
//
//    private final AppointmentService service;
//
//    @Autowired
//    public AppointmentController(AppointmentService service) {
//        this.service = service;
//    }
//
//    @GetMapping
//    public ResponseEntity<?> getAllAppointments() {
//        return ResponseEntity.ok(service.findAll());
//    }
//
//    @PostMapping
//    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
//        return ResponseEntity.ok(service.save(appointment));
//    }
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<String> handleInvalidInput(IllegalArgumentException ex) {
//        return ResponseEntity.badRequest().body(ex.getMessage());
//    }
//
//}
