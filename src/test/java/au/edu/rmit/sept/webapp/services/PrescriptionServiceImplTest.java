package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.PetInformation;
import au.edu.rmit.sept.webapp.models.Prescription;
import au.edu.rmit.sept.webapp.repositories.PrescriptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PrescriptionServiceImplTest {

    @Mock
    private PrescriptionRepository prescriptionRepository;

    @InjectMocks
    private PrescriptionServiceImpl prescriptionService;

    private Prescription prescription1;
    private Prescription prescription2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        PetInformation pet = new PetInformation(1L, "Buddy", 3, "Male", 25.5, "Golden Retriever", null, 1L);

        prescription1 = new Prescription(
                1L,
                "Fidozol 50mg",
                "1 tablet twice a day",
                "Take with food",
                LocalDate.of(2024, 10, 15),
                30,
                2,
                LocalDate.of(2025, 10, 15),
                pet,
                LocalDate.now()
        );

        prescription2 = new Prescription(
                2L,
                "AllerPet 10mg",
                "1 tablet daily",
                "Administer with food",
                LocalDate.of(2024, 6, 15),
                60,
                3,
                LocalDate.of(2025, 6, 15),
                pet,
                LocalDate.now()
        ); }

    @Test
    void testGetAllPrescriptions() {
        when(prescriptionRepository.findAll()).thenReturn(List.of(prescription1, prescription2));

        List<Prescription> result = prescriptionService.getAllPrescriptions();

        assertEquals(2, result.size());
        assertEquals("Fidozol 50mg", result.get(0).getMedicationName());
        assertEquals("AllerPet 10mg", result.get(1).getMedicationName());

        verify(prescriptionRepository, times(1)).findAll();
    }

    @Test
    void testGetPrescriptionsByPetId() {

        when(prescriptionRepository.findByPetId(1L)).thenReturn(List.of(prescription1));

        List<Prescription> result = prescriptionService.getPrescriptionsByPetId(1L);

        assertEquals(1, result.size());
        assertEquals("Fidozol 50mg", result.get(0).getMedicationName());

        verify(prescriptionRepository, times(1)).findByPetId(1L);
    }

    @Test
    void testCreatePrescription() {

        prescriptionService.createPrescription(prescription1);

        verify(prescriptionRepository, times(1)).addPrescription(prescription1);
    }
}
