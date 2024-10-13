package au.edu.rmit.sept.webapp.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClinicServicePricingTest {

    private ClinicServicePricing clinicServicePricing;

    @BeforeEach
    public void setUp() {
        clinicServicePricing = new ClinicServicePricing();
    }

    @Test
    public void testNoArgsConstructor() {
        assertNotNull(clinicServicePricing);
    }

    @Test
    public void testAllArgsConstructor() {
        Long clinicId = 1L;
        Long serviceId = 2L;
        float price = 99.99f;
        String clinicName = "City Vet Clinic";

        ClinicServicePricing clinicServicePricing = new ClinicServicePricing(clinicId, serviceId, price, clinicName);

        assertEquals(clinicId, clinicServicePricing.getClinicId());
        assertEquals(serviceId, clinicServicePricing.getServiceId());
        assertEquals(price, clinicServicePricing.getPrice());
        assertEquals(clinicName, clinicServicePricing.getClinicName());
    }

    @Test
    public void testSettersAndGetters() {
        Long clinicId = 1L;
        Long serviceId = 2L;
        float price = 120.50f;
        String clinicName = "Suburb Vet Clinic";

        clinicServicePricing.setClinicId(clinicId);
        clinicServicePricing.setServiceId(serviceId);
        clinicServicePricing.setPrice(price);
        clinicServicePricing.setClinicName(clinicName);

        assertEquals(clinicId, clinicServicePricing.getClinicId());
        assertEquals(serviceId, clinicServicePricing.getServiceId());
        assertEquals(price, clinicServicePricing.getPrice());
        assertEquals(clinicName, clinicServicePricing.getClinicName());
    }
}
