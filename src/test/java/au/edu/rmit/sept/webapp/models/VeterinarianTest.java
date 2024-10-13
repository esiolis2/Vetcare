package au.edu.rmit.sept.webapp.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VeterinarianTest {

    private Veterinarian veterinarian;

    @BeforeEach
    public void setUp() {
        veterinarian = new Veterinarian();
    }

    @Test
    public void testNoArgsConstructor() {
        assertNotNull(veterinarian);
    }

    @Test
    public void testAllArgsConstructor() {
        Long id = 1L;
        String name = "Dr. John Doe";
        String email = "john.doe@example.com";
        Long clinicId = 100L;

        Veterinarian veterinarian = new Veterinarian(id, name, email, clinicId);

        assertEquals(id, veterinarian.getId());
        assertEquals(name, veterinarian.getName());
        assertEquals(email, veterinarian.getEmail());
        assertEquals(clinicId, veterinarian.getClinicId());
    }

    @Test
    public void testSettersAndGetters() {
        Long id = 2L;
        String name = "Dr. Jane Smith";
        String email = "jane.smith@example.com";
        Long clinicId = 200L;

        veterinarian.setId(id);
        veterinarian.setName(name);
        veterinarian.setEmail(email);
        veterinarian.setClinicId(clinicId);

        assertEquals(id, veterinarian.getId());
        assertEquals(name, veterinarian.getName());
        assertEquals(email, veterinarian.getEmail());
        assertEquals(clinicId, veterinarian.getClinicId());
    }

    @Test
    public void testEmptyFields() {
        assertNull(veterinarian.getId());
        assertNull(veterinarian.getName());
        assertNull(veterinarian.getEmail());
        assertNull(veterinarian.getClinicId());
    }

    @Test
    public void testUpdateVeterinarianDetails() {
        veterinarian.setName("Dr. Alice Johnson");
        veterinarian.setEmail("alice.johnson@example.com");
        veterinarian.setClinicId(300L);

        assertEquals("Dr. Alice Johnson", veterinarian.getName());
        assertEquals("alice.johnson@example.com", veterinarian.getEmail());
        assertEquals(300L, veterinarian.getClinicId());
    }
}
