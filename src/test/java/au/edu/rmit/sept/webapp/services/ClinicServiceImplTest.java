package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.Clinic;
import au.edu.rmit.sept.webapp.repositories.ClinicRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClinicServiceImplTest {

    private ClinicServiceImpl clinicService;
    private ClinicRepository clinicRepository;

    @BeforeEach
    public void setUp() {
        clinicRepository = Mockito.mock(ClinicRepository.class);
        clinicService = new ClinicServiceImpl(clinicRepository);
    }

    @Test
    public void testGetAllClinics_ShouldReturnListOfClinics() {
        Clinic clinic1 = new Clinic();
        Clinic clinic2 = new Clinic();
        List<Clinic> clinics = Arrays.asList(clinic1, clinic2);

        when(clinicRepository.findAll()).thenReturn(clinics);

        List<Clinic> result = clinicService.getAllClinics();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(clinicRepository, times(1)).findAll();
    }

    @Test
    public void testGetClinicById_ShouldReturnClinic() {
        Clinic clinic = new Clinic();
        clinic.setId(1L);

        when(clinicRepository.findClinicById(1L)).thenReturn(clinic);

        Clinic result = clinicService.getClinicById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(clinicRepository, times(1)).findClinicById(1L);
    }

    @Test
    public void testGetClinicById_ShouldReturnNullForNonExistentClinic() {
        when(clinicRepository.findClinicById(1L)).thenReturn(null);

        Clinic result = clinicService.getClinicById(1L);

        assertNull(result);
        verify(clinicRepository, times(1)).findClinicById(1L);
    }
}