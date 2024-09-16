package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.Appointment;
import java.util.Collection;

public interface AppointmentService {
    public Collection<Appointment> getAppointments();
    public Appointment createAppointment (Appointment appointment);
    public boolean deleteAppointment (Long appointmentId);


}
