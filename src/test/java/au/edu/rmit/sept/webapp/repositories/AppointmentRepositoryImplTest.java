package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.Appointment;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AppointmentRepositoryImplTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private Flyway flyway;

    private AppointmentRepository appointmentRepository;

    @BeforeEach
    public void setUp() {
        // Run migrations to set up the database for each test
        flyway.migrate();
        appointmentRepository = new AppointmentRepositoryImpl(dataSource);
    }

    @AfterEach
    public void tearDown() {
        // Clean up the database after each test
        flyway.clean();
    }

    @Test
    public void testFindAll_ShouldReturnListOfAppointmentsForUser() {
        // Arrange
        Long validUserId = 1L; // Assuming user with ID 1 exists

        // Act
        List<Appointment> appointments = appointmentRepository.findAll(validUserId);

        // Assert
        assertNotNull(appointments);
        assertFalse(appointments.isEmpty(), "Appointments list should not be empty.");
    }

    @Test
    public void testFindById_ShouldReturnAppointmentForValidId() {
        // Arrange
        Long validAppointmentId = 1L; // Assuming an appointment with ID 1 exists

        // Act
        Appointment appointment = appointmentRepository.findById(validAppointmentId);

        // Assert
        assertNotNull(appointment);
        assertEquals(validAppointmentId, appointment.getId());
    }

    @Test
    public void testFindById_ShouldReturnNullForNonExistentAppointment() {
        // Arrange
        Long invalidAppointmentId = 999L; // Appointment ID that doesn't exist

        // Act
        Appointment appointment = appointmentRepository.findById(invalidAppointmentId);

        // Assert
        assertNull(appointment, "Expected no appointment for a non-existent ID");
    }

    @Test
    public void testAddAppointment_ShouldSuccessfullyAddAppointment() {
        // Arrange
        Appointment newAppointment = new Appointment();
        newAppointment.setVeterinarianId(1L);
        newAppointment.setClinicId(1L);
        newAppointment.setUserId(1L);
        newAppointment.setPetId(1L);
        newAppointment.setReason("Routine checkup");
        newAppointment.setAppointmentDate(LocalDate.now().plusDays(1));
        newAppointment.setAppointmentTime(LocalTime.of(10, 0));

        // Act
        Appointment addedAppointment = appointmentRepository.addAppointment(newAppointment);

        // Assert
        assertNotNull(addedAppointment.getId(), "Appointment ID should be generated.");
        assertEquals("Routine checkup", addedAppointment.getReason());
    }

    @Test
    public void testUpdateAppointment_ShouldUpdateAppointmentDetails() {
        // Arrange
        Long appointmentIdToUpdate = 1L; // Assuming an appointment with ID 1 exists
        Appointment appointment = appointmentRepository.findById(appointmentIdToUpdate);
        appointment.setReason("Updated reason");

        // Act
        Appointment updatedAppointment = appointmentRepository.updateAppointment(appointment);

        // Assert
        assertEquals("Updated reason", updatedAppointment.getReason(), "Appointment reason should be updated.");
    }

    @Test
    public void testDeleteAppointment_ShouldRemoveAppointment() {
        // Arrange
        Long appointmentIdToDelete = 2L; // Assuming an appointment with ID 2 exists

        // Act
        boolean isDeleted = appointmentRepository.removeAppointment(appointmentIdToDelete);

        // Assert
        assertTrue(isDeleted, "Appointment should be deleted successfully.");

        // Verify that the appointment no longer exists
        Appointment appointment = appointmentRepository.findById(appointmentIdToDelete);
        assertNull(appointment, "Appointment should not exist after deletion.");
    }
}
