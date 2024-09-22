package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.Appointment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository {
    public List<Appointment> findAll(Long userId);
    public Appointment addAppointment(Appointment appointment);
    public Appointment updateAppointment(Appointment appointment);
    public Appointment findById(Long id);
    public boolean removeAppointment(Long appointmentId);


}
