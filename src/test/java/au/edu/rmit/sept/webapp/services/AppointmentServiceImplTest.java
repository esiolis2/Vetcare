package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.Appointment;
import au.edu.rmit.sept.webapp.repositories.AppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AppointmentServiceImplTest {

    private AppointmentServiceImpl appointmentService;
    private AppointmentRepository appointmentRepository;

    @BeforeEach
    public void setUp() {
        appointmentRepository = Mockito.mock(AppointmentRepository.class);
        appointmentService = new AppointmentServiceImpl(appointmentRepository);
    }

    @Test
    public void testGetAppointments_ShouldReturnListOfAppointments() {
        // Arrange
        Long userId = 1L;
        Appointment appointment1 = new Appointment();
        Appointment appointment2 = new Appointment();
        List<Appointment> appointments = Arrays.asList(appointment1, appointment2);

        when(appointmentRepository.findAll(userId)).thenReturn(appointments);

        // Act
        List<Appointment> result = appointmentService.getAppointments(userId);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(appointmentRepository, times(1)).findAll(userId);
    }

    @Test
    public void testCreateAppointment_ShouldReturnCreatedAppointment() {
        // Arrange
        Appointment newAppointment = new Appointment();
        newAppointment.setVeterinarianId(1L);
        newAppointment.setClinicId(1L);
        newAppointment.setUserId(1L);
        newAppointment.setPetId(1L);
        newAppointment.setReason(1L);
        newAppointment.setAppointmentDate(LocalDate.now().plusDays(1));
        newAppointment.setAppointmentTime(LocalTime.of(10, 0));

        when(appointmentRepository.addAppointment(newAppointment)).thenReturn(newAppointment);

        // Act
        Appointment result = appointmentService.createAppointment(newAppointment);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getReason());
        verify(appointmentRepository, times(1)).addAppointment(newAppointment);
    }

    @Test
    public void testRescheduleAppointment_ShouldReturnUpdatedAppointment() {
        // Arrange
        Appointment existingAppointment = new Appointment();
        existingAppointment.setId(1L);
        existingAppointment.setReason(1L);

        when(appointmentRepository.updateAppointment(existingAppointment)).thenReturn(existingAppointment);

        // Act
        Appointment result = appointmentService.rescheduleAppointment(existingAppointment);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getReason());
        verify(appointmentRepository, times(1)).updateAppointment(existingAppointment);
    }

    @Test
    public void testFindAppointmentById_ShouldReturnAppointment() {
        // Arrange
        Long appointmentId = 1L;
        Appointment appointment = new Appointment();
        appointment.setId(appointmentId);

        when(appointmentRepository.findById(appointmentId)).thenReturn(appointment);

        // Act
        Appointment result = appointmentService.findAppointmentById(appointmentId);

        // Assert
        assertNotNull(result);
        assertEquals(appointmentId, result.getId());
        verify(appointmentRepository, times(1)).findById(appointmentId);
    }

    @Test
    public void testFindAppointmentById_ShouldReturnNullForNonExistentId() {
        // Arrange
        Long appointmentId = 999L;

        when(appointmentRepository.findById(appointmentId)).thenReturn(null);

        // Act
        Appointment result = appointmentService.findAppointmentById(appointmentId);

        // Assert
        assertNull(result);
        verify(appointmentRepository, times(1)).findById(appointmentId);
    }

    @Test
    public void testDeleteAppointment_ShouldReturnTrue() {
        // Arrange
        Long appointmentId = 1L;

        when(appointmentRepository.removeAppointment(appointmentId)).thenReturn(true);

        // Act
        boolean result = appointmentService.deleteAppointment(appointmentId);

        // Assert
        assertTrue(result);
        verify(appointmentRepository, times(1)).removeAppointment(appointmentId);
    }

    @Test
    public void testDeleteAppointment_ShouldReturnFalseForNonExistentId() {
        // Arrange
        Long appointmentId = 999L;

        when(appointmentRepository.removeAppointment(appointmentId)).thenReturn(false);

        // Act
        boolean result = appointmentService.deleteAppointment(appointmentId);

        // Assert
        assertFalse(result);
        verify(appointmentRepository, times(1)).removeAppointment(appointmentId);
    }

    @Test
    public void testGetUpcomingAppointments_ShouldReturnUpcomingAppointments() {
        Long userId = 1L;
        LocalDate today = LocalDate.now();
        Appointment upcomingAppointment = new Appointment();
        upcomingAppointment.setAppointmentDate(today.plusDays(3));

        Appointment pastAppointment = new Appointment();
        pastAppointment.setAppointmentDate(today.minusDays(3));

        when(appointmentRepository.findAll(userId)).thenReturn(Arrays.asList(upcomingAppointment, pastAppointment));

        List<Appointment> result = appointmentService.getUpcomingAppointments(userId);

        assertEquals(1, result.size());
        assertTrue(result.contains(upcomingAppointment));
        assertFalse(result.contains(pastAppointment));
        verify(appointmentRepository, times(1)).findAll(userId);
    }

    @Test
    public void testGetAppointmentsByVeterinarian_ShouldReturnAppointmentsForVet() {
        Long vetId = 1L;
        Appointment appointment1 = new Appointment();
        Appointment appointment2 = new Appointment();
        List<Appointment> appointments = Arrays.asList(appointment1, appointment2);

        when(appointmentRepository.findAllByVetId(vetId)).thenReturn(appointments);

        List<Appointment> result = appointmentService.getAppointmentsByVeterinarian(vetId);

        assertEquals(2, result.size());
        verify(appointmentRepository, times(1)).findAllByVetId(vetId);
    }

    @Test
    public void testGetUpcomingAppointments_ShouldReturnEmptyListForNoUpcomingAppointments() {
        Long userId = 1L;
        LocalDate today = LocalDate.now();
        Appointment pastAppointment = new Appointment();
        pastAppointment.setAppointmentDate(today.minusDays(5));

        when(appointmentRepository.findAll(userId)).thenReturn(List.of(pastAppointment));

        List<Appointment> result = appointmentService.getUpcomingAppointments(userId);

        assertTrue(result.isEmpty());
        verify(appointmentRepository, times(1)).findAll(userId);
    }

    @Test
    public void testGetAppointments_ShouldReturnEmptyListIfNoAppointmentsFound() {
        Long userId = 999L;

        when(appointmentRepository.findAll(userId)).thenReturn(List.of());

        List<Appointment> result = appointmentService.getAppointments(userId);

        assertTrue(result.isEmpty());
        verify(appointmentRepository, times(1)).findAll(userId);
    }
}
