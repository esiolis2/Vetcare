package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.Appointment;
import java.util.Collection;
import java.util.List;

public interface AppointmentService {
    public List<Appointment> getAppointments(Long userId);
    public Appointment createAppointment (Appointment appointment);
    public Appointment rescheduleAppointment (Appointment appointment);
    public Appointment findAppointmentById(Long appointmentId);
    public boolean deleteAppointment (Long appointmentId);
    public List<Appointment> getUpcomingAppointments(Long userId);
    public List<Appointment> getAppointmentsByVeterinarian(Long vetId);

}
