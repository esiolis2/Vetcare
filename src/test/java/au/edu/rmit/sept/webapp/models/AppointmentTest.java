package au.edu.rmit.sept.webapp.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class AppointmentTest {

    private Appointment appointment;

    @BeforeEach
    public void setUp() {
        appointment = new Appointment();
    }

    @Test
    public void testNoArgsConstructor() {
        assertNotNull(appointment);
    }

    @Test
    public void testAllArgsConstructorWithoutUserId() {

        Long id = 1L;
        Long veterinarianId = 2L;
        Long clinicId = 3L;
        LocalTime appointmentTime = LocalTime.of(10, 30);
        LocalDate appointmentDate = LocalDate.of(2024, 10, 13);
        Long reason = 5L;
        Long petId = 6L;

        Appointment appt = new Appointment(id, veterinarianId, clinicId, null, appointmentTime, appointmentDate, reason, petId);

        assertEquals(id, appt.getId());
        assertEquals(veterinarianId, appt.getVeterinarianId());
        assertEquals(clinicId, appt.getClinicId());
        assertEquals(appointmentTime, appt.getAppointmentTime());
        assertEquals(appointmentDate, appt.getAppointmentDate());
        assertEquals(reason, appt.getReason());
        assertEquals(petId, appt.getPetId());
    }

    @Test
    public void testSettersAndGetters() {
        Long id = 1L;
        Long veterinarianId = 2L;
        Long clinicId = 3L;
        Long userId = 4L;
        LocalTime appointmentTime = LocalTime.of(9, 0);
        LocalDate appointmentDate = LocalDate.of(2024, 11, 15);
        Long reason = 10L;
        Long petId = 7L;

        appointment.setId(id);
        appointment.setVeterinarianId(veterinarianId);
        appointment.setClinicId(clinicId);
        appointment.setUserId(userId);
        appointment.setAppointmentTime(appointmentTime);
        appointment.setAppointmentDate(appointmentDate);
        appointment.setReason(reason);
        appointment.setPetId(petId);

        assertEquals(id, appointment.getId());
        assertEquals(veterinarianId, appointment.getVeterinarianId());
        assertEquals(clinicId, appointment.getClinicId());
        assertEquals(userId, appointment.getUserId());
        assertEquals(appointmentTime, appointment.getAppointmentTime());
        assertEquals(appointmentDate, appointment.getAppointmentDate());
        assertEquals(reason, appointment.getReason());
        assertEquals(petId, appointment.getPetId());
    }
}
