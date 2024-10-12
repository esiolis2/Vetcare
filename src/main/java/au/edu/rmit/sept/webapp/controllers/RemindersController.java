package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.models.*;
import au.edu.rmit.sept.webapp.services.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RemindersController {

    private final AppointmentService appointmentService;
    private final UserService userService;
    private final PetInformationService petInformationService;
    private final PrescriptionService prescriptionService;
    private final PrescriptionRefillService prescriptionRefillService;

    public RemindersController(AppointmentService appointmentService, UserService userService, PetInformationService petInformationService, PrescriptionService prescriptionService, PrescriptionRefillService prescriptionRefillService) {
        this.appointmentService = appointmentService;
        this.userService = userService;
        this.petInformationService = petInformationService;
        this.prescriptionService = prescriptionService;
        this.prescriptionRefillService = prescriptionRefillService;
    }

    @ModelAttribute
    public String getAppointmentNotifications(HttpServletRequest request, Model model) {
        String email = (String) request.getSession().getAttribute("userEmail");
        if (email != null) {
            User user = userService.findByEmail(email);
            Long userId = user.getId();
            List<Appointment> upcomingAppointments = appointmentService.getUpcomingAppointments(userId);
            List<PetInformation> pets = petInformationService.getPetByUserId(userId);
            List<Prescription> upcomingPrescriptions = new ArrayList<>();
            List<PrescriptionRefillRequest> refills = prescriptionRefillService.getAllRefillRequests();
            List<PrescriptionRefillRequest> refillRequests = new ArrayList<>();

            for (PetInformation pet : pets) {
                List<Prescription> petPrescriptions = prescriptionService.getPrescriptionsByPetId(pet.getPetID());
                LocalDate now = LocalDate.now();
                LocalDate nextWeek = now.plusDays(7);
                for (Prescription prescription : petPrescriptions) {
                    prescription.setPet(pet);
                    LocalDate nextRefillDate = prescription.getNextRefillDate();
                    if (nextRefillDate != null && nextRefillDate.isAfter(now) && nextRefillDate.isBefore(nextWeek)) {
                        upcomingPrescriptions.add(prescription);
                    }
                }
                for (PrescriptionRefillRequest refill : refills) {
                    LocalDateTime twoDays = LocalDateTime.now().plusDays(2);
                    if (refill.getPetID().equals(pet.getPetID()) && refill.getRequestDate().isAfter(LocalDateTime.now()) &&  refill.getRequestDate().isBefore(twoDays)) {
                        refillRequests.add(refill);
                    }
                }
            }




            int numberOfNotifications = upcomingPrescriptions.size() + upcomingAppointments.size() + refills.size();
            model.addAttribute("upcomingAppointments", upcomingAppointments);
            model.addAttribute("upcomingPrescriptions", upcomingPrescriptions);
            model.addAttribute("numberOfNotifications", numberOfNotifications);
            model.addAttribute("refills", refills);
            model.addAttribute("pets", pets);
        }
        return "layout";
    }


}
