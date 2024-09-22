package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.ClinicReasons;
import au.edu.rmit.sept.webapp.repositories.ClinicReasonsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClinicReasonsServiceImplTest {

    private ClinicReasonsServiceImpl clinicReasonsService;
    private ClinicReasonsRepository clinicReasonsRepository;

    @BeforeEach
    public void setUp() {
        clinicReasonsRepository = Mockito.mock(ClinicReasonsRepository.class);
        clinicReasonsService = new ClinicReasonsServiceImpl(clinicReasonsRepository);
    }

    @Test
    public void testGetAllClinicReasons_ShouldReturnListOfClinicReasons() {
        ClinicReasons reason1 = new ClinicReasons();
        ClinicReasons reason2 = new ClinicReasons();
        List<ClinicReasons> reasons = Arrays.asList(reason1, reason2);

        when(clinicReasonsRepository.findAll()).thenReturn(reasons);

        List<ClinicReasons> result = clinicReasonsService.getAllClinicReasons();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(clinicReasonsRepository, times(1)).findAll();
    }

    @Test
    public void testGetAllClinicReasons_ShouldReturnEmptyListWhenNoData() {
        when(clinicReasonsRepository.findAll()).thenReturn(Arrays.asList());

        List<ClinicReasons> result = clinicReasonsService.getAllClinicReasons();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(clinicReasonsRepository, times(1)).findAll();
    }
}