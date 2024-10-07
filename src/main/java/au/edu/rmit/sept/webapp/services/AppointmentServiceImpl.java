package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.Appointment;
import au.edu.rmit.sept.webapp.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    public  AppointmentServiceImpl (AppointmentRepository appointmentRepository){
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<Appointment> getAppointments(Long userId) {
        return appointmentRepository.findAll(userId);
    }

    @Override
    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.addAppointment(appointment);
    }

    @Override
    public Appointment rescheduleAppointment(Appointment appointment) {
        return appointmentRepository.updateAppointment(appointment);
    }

    @Override
    public Appointment findAppointmentById(Long appointmentId) {
        return appointmentRepository.findById(appointmentId);
    }

    @Override
    public boolean deleteAppointment(Long appointmentId) {
        return appointmentRepository.removeAppointment(appointmentId);
    }

    public List<Appointment> getUpcomingAppointments(Long userId) {
        LocalDate now = LocalDate.now();
        LocalDate nextWeek = now.plusDays(7);

        List<Appointment> allAppointments = appointmentRepository.findAll(userId);
        List<Appointment> upcomingAppointments = new ArrayList<>();
        for (Appointment appointment : allAppointments) {
            if (appointment.getAppointmentDate().isAfter(now) && appointment.getAppointmentDate().isBefore(nextWeek)) {
                upcomingAppointments.add(appointment);
            }
        }
        return upcomingAppointments;
    }
}
