package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.ClinicServicePricing;
import au.edu.rmit.sept.webapp.repositories.ClinicServicePricingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ClinicServicePricingServiceImplTest {

    private ClinicServicePricingService clinicServicePricingService;
    private ClinicServicePricingRepository clinicServicePricingRepository;

    @BeforeEach
    public void setUp() {
        clinicServicePricingRepository = Mockito.mock(ClinicServicePricingRepository.class);
        clinicServicePricingService = new ClinicServicePricingServiceImpl(clinicServicePricingRepository);
    }

    @Test
    public void getAllById_shouldReturnCorrectPricing() {
        ClinicServicePricing pricing1 = new ClinicServicePricing(1L, 1L, 50.0f, "Clinic A");
        ClinicServicePricing pricing2 = new ClinicServicePricing(2L, 1L, 75.0f, "Clinic B");

        when(clinicServicePricingRepository.findAllById(1)).thenReturn(List.of(pricing1, pricing2));

        List<ClinicServicePricing> pricingList = clinicServicePricingService.getAllById(1);

        assertEquals(2, pricingList.size());
        assertEquals("Clinic A", pricingList.get(0).getClinicName());
        assertEquals(50.0f, pricingList.get(0).getPrice());
        verify(clinicServicePricingRepository, times(1)).findAllById(1);
    }

    @Test
    public void getAll_shouldReturnAllPricing() {
        ClinicServicePricing pricing1 = new ClinicServicePricing(1L, 1L, 50.0f, "Clinic A");
        ClinicServicePricing pricing2 = new ClinicServicePricing(2L, 2L, 100.0f, "Clinic B");

        when(clinicServicePricingRepository.findAll()).thenReturn(List.of(pricing1, pricing2));

        List<ClinicServicePricing> pricingList = clinicServicePricingService.getAll();

        assertEquals(2, pricingList.size());
        assertEquals("Clinic A", pricingList.get(0).getClinicName());
        assertEquals("Clinic B", pricingList.get(1).getClinicName());
        verify(clinicServicePricingRepository, times(1)).findAll();
    }
}
