package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.Appointment;
import au.edu.rmit.sept.webapp.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

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
}
