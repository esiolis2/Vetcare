package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.PrescriptionRefillRequest;
import au.edu.rmit.sept.webapp.repositories.PrescriptionRefillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PrescriptionRefillServiceImplTest {

    @Mock
    private PrescriptionRefillRepository prescriptionRefillRepository;

    @InjectMocks
    private PrescriptionRefillServiceImpl prescriptionRefillService;

    private PrescriptionRefillRequest refillRequest1;
    private PrescriptionRefillRequest refillRequest2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        refillRequest1 = new PrescriptionRefillRequest(
                1L,
                1L,
                List.of("Medication A", "Medication B"),
                "123 Main St",
                "Melbourne",
                "VIC",
                "3000",
                "Notes for refill request 1",
                LocalDateTime.now()
        );

        refillRequest2 = new PrescriptionRefillRequest(
                2L,
                2L,
                List.of("Medication C"),
                "456 Elm St",
                "Sydney",
                "NSW",
                "2000",
                "Notes for refill request 2",
                LocalDateTime.now()
        );
    }

    @Test
    void testSaveRefillRequest() {
        prescriptionRefillService.saveRefillRequest(refillRequest1);

        verify(prescriptionRefillRepository, times(1)).saveRefillRequest(refillRequest1);
    }

    @Test
    void testGetAllRefillRequests() {
        when(prescriptionRefillRepository.findAllRefillRequests()).thenReturn(List.of(refillRequest1, refillRequest2));

        List<PrescriptionRefillRequest> result = prescriptionRefillService.getAllRefillRequests();

        assertEquals(2, result.size());
        assertEquals("Medication A", result.get(0).getMedications().get(0));
        assertEquals("Medication C", result.get(1).getMedications().get(0));

        verify(prescriptionRefillRepository, times(1)).findAllRefillRequests();
    }
}
