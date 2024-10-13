package au.edu.rmit.sept.webapp.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClinicReasonsTest {

    private ClinicReasons clinicReasons;

    @BeforeEach
    public void setUp() {
        clinicReasons = new ClinicReasons();
    }

    @Test
    public void testNoArgsConstructor() {
        assertNotNull(clinicReasons);
    }

    @Test
    public void testAllArgsConstructor() {
        int id = 1;
        String serviceName = "Vaccination";

        ClinicReasons clinicReasons = new ClinicReasons(id, serviceName);

        assertEquals(id, clinicReasons.getId());
        assertEquals(serviceName, clinicReasons.getServiceName());
    }

    @Test
    public void testSettersAndGetters() {
        int id = 2;
        String serviceName = "Neutering";

        clinicReasons.setId(id);
        clinicReasons.setServiceName(serviceName);

        assertEquals(id, clinicReasons.getId());
        assertEquals(serviceName, clinicReasons.getServiceName());
    }
}
