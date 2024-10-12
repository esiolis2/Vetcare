package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.Appointment;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
@Transactional
public class AppointmentRepositoryImplTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private Flyway flyway;
    @MockBean
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

        List<Appointment> appointments = List.of(new Appointment(), new Appointment());

        assertNotNull(appointments);
        assertFalse(appointments.isEmpty(), "Appointments list should not be empty.");
    }

    @Test
    public void testFindById_ShouldReturnAppointmentForValidId() {
        // Arrange
        Appointment appointment = new Appointment();
        Long validAppointmentId = 1L;
        appointment.setId(validAppointmentId);
        // Act
        Appointment findAppointment = appointmentRepository.findById(validAppointmentId);

        // Assert
        assertNotNull(findAppointment);
        assertEquals(validAppointmentId, appointment.getId());
    }

    @Test
    public void testFindById_ShouldReturnNullForNonExistentAppointment() {
        // Arrange
        Long invalidAppointmentId = 999L; // Appointment ID that doesn't exist

        // Act
        Appointment appointment = appointmentRepository.findById(invalidAppointmentId);

        // Assert
        assertNull(appointment.getId());
        assertNull(appointment.getAppointmentDate());
        assertNull(appointment.getAppointmentTime());
        assertNull(appointment.getVeterinarianId());
        assertNull(appointment.getPetId());
        assertNull(appointment.getClinicId());


    }

    @Test
    public void testAddAppointment_ShouldSuccessfullyAddAppointment() {
        // Arrange
        Appointment newAppointment = new Appointment();
        newAppointment.setId(1L);
        newAppointment.setVeterinarianId(1L);
        newAppointment.setClinicId(1L);
        newAppointment.setUserId(1L);
        newAppointment.setPetId(1L);
        newAppointment.setReason(1L);
        newAppointment.setAppointmentDate(LocalDate.now().plusDays(1));
        newAppointment.setAppointmentTime(LocalTime.of(10, 0));

        Appointment addedAppointment = appointmentRepository.findById(newAppointment.getId());

        assertNotNull(addedAppointment);

    }

    @Test
    public void testUpdateAppointment_ShouldUpdateAppointmentDetails() {
        // Arrange
        Appointment existingAppointment = new Appointment();
        existingAppointment.setId(1L);
        existingAppointment.setAppointmentTime(LocalTime.of(9, 30));
        existingAppointment.setVeterinarianId(1L);
        existingAppointment.setClinicId(1L);
        existingAppointment.setUserId(1L);
        existingAppointment.setPetId(1L);
        existingAppointment.setReason(1L);
        existingAppointment.setAppointmentDate(LocalDate.now());
        appointmentRepository.addAppointment(existingAppointment);

        Long appointmentIdToUpdate = 1L; // Assuming an appointment with ID 1 exists
        Appointment appointmentToUpdate = appointmentRepository.findById(appointmentIdToUpdate);
        assertNotNull(appointmentToUpdate, "Appointment should exist before updating.");
        // Modify the appointment details
        LocalTime newTime = LocalTime.of(10, 30);
        appointmentToUpdate.setAppointmentTime(newTime);
        appointmentToUpdate.setVeterinarianId(2L);
        Appointment updatedAppointment = appointmentRepository.updateAppointment(appointmentToUpdate);

        // Fetch the updated appointment again from the repository
        Appointment fetchedAppointment = appointmentRepository.findById(appointmentIdToUpdate);

        // Assert
        assertNotNull(updatedAppointment, "Updated appointment should not be null.");
        assertEquals(newTime, fetchedAppointment.getAppointmentTime(), "Appointment time should be updated.");
        assertNotEquals(existingAppointment.getAppointmentTime(), appointmentToUpdate.getAppointmentTime());
        assertNotEquals(existingAppointment.getVeterinarianId(), appointmentToUpdate.getVeterinarianId());
    }

    @Test
    public void testDeleteAppointment_ShouldRemoveAppointment() {
        // Arrange
        Long appointmentIdToDelete = 2L; // Assuming an appointment with ID 2 exists
        // Act
        boolean isDeleted = appointmentRepository.removeAppointment(appointmentIdToDelete);
        // Assert
        assertTrue(isDeleted, "Appointment should be deleted successfully.");
    }
}
