package au.edu.rmit.sept.webapp.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrescriptionRefillRequestTest {

    @Test
    public void testPrescriptionRefillRequestConstructorAndGetters() {
        LocalDateTime requestDate = LocalDateTime.now();
        List<String> medications = List.of("AllerPet 10mg", "Fidozol 50mg");

        PrescriptionRefillRequest refillRequest = new PrescriptionRefillRequest(
                1L,
                2L,
                medications,
                "123 Main St",
                "Springfield",
                "IL",
                "62701",
                "Take with food",
                requestDate
        );

        assertEquals(1L, refillRequest.getRequestID());
        assertEquals(2L, refillRequest.getPetID());
        assertEquals(medications, refillRequest.getMedications());
        assertEquals("123 Main St", refillRequest.getAddress());
        assertEquals("Springfield", refillRequest.getCity());
        assertEquals("IL", refillRequest.getState());
        assertEquals("62701", refillRequest.getPostcode());
        assertEquals("Take with food", refillRequest.getNotes());
        assertEquals(requestDate, refillRequest.getRequestDate());
    }

    @Test
    public void testSettersForPrescriptionRefillRequest() {
        PrescriptionRefillRequest refillRequest = new PrescriptionRefillRequest();
        LocalDateTime now = LocalDateTime.now();
        List<String> medications = List.of("Cortavance", "NexGard");

        refillRequest.setRequestID(3L);
        refillRequest.setPetID(4L);
        refillRequest.setMedications(medications);
        refillRequest.setAddress("456 Oak St");
        refillRequest.setCity("Chicago");
        refillRequest.setState("IL");
        refillRequest.setPostcode("60616");
        refillRequest.setNotes("Administer twice daily");
        refillRequest.setRequestDate(now);

        assertEquals(3L, refillRequest.getRequestID());
        assertEquals(4L, refillRequest.getPetID());
        assertEquals(medications, refillRequest.getMedications());
        assertEquals("456 Oak St", refillRequest.getAddress());
        assertEquals("Chicago", refillRequest.getCity());
        assertEquals("IL", refillRequest.getState());
        assertEquals("60616", refillRequest.getPostcode());
        assertEquals("Administer twice daily", refillRequest.getNotes());
        assertEquals(now, refillRequest.getRequestDate());
    }
}
