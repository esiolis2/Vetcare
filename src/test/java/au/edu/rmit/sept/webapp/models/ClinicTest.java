package au.edu.rmit.sept.webapp.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClinicTest {

    private Clinic clinic;

    @BeforeEach
    public void setUp() {
        clinic = new Clinic();
    }

    @Test
    public void testNoArgsConstructor() {
        assertNotNull(clinic);
    }

    @Test
    public void testAllArgsConstructor() {
        Long id = 1L;
        String name = "City Vet Clinic";
        String address = "123 Main St";
        String phone = "123-456-7890";

        Clinic clinic = new Clinic(id, name, address, phone);

        assertEquals(id, clinic.getId());
        assertEquals(name, clinic.getName());
        assertEquals(address, clinic.getAddress());
        assertEquals(phone, clinic.getPhone());
    }

    @Test
    public void testSettersAndGetters() {
        Long id = 2L;
        String name = "Suburb Vet Clinic";
        String address = "456 El St";
        String phone = "987-654-3210";

        clinic.setId(id);
        clinic.setName(name);
        clinic.setAddress(address);
        clinic.setPhone(phone);

        assertEquals(id, clinic.getId());
        assertEquals(name, clinic.getName());
        assertEquals(address, clinic.getAddress());
        assertEquals(phone, clinic.getPhone());
    }

    @Test
    public void testAdditionalConstructorWithExtraParam() {
        Long id = 3L;
        String name = "Downtown Vet Clinic";
        String address = "789 Oak St";
        String phone = "999-123-4567";

        Clinic clinic = new Clinic(id, name, address, phone);

        assertEquals(id, clinic.getId());
        assertEquals(name, clinic.getName());
        assertEquals(address, clinic.getAddress());
        assertEquals(phone, clinic.getPhone());
    }
}
