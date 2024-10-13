package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.models.Clinic;
import au.edu.rmit.sept.webapp.models.ClinicReasons;
import au.edu.rmit.sept.webapp.models.ClinicServicePricing;
import au.edu.rmit.sept.webapp.models.Veterinarian;
import au.edu.rmit.sept.webapp.services.ClinicReasonsService;
import au.edu.rmit.sept.webapp.services.ClinicService;
import au.edu.rmit.sept.webapp.services.ClinicServicePricingService;
import au.edu.rmit.sept.webapp.services.VeterinarianService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
@ActiveProfiles("test")
public class ClinicControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private ClinicService clinicService;

    @MockBean
    private VeterinarianService veterinarianService;

    @MockBean
    private ClinicServicePricingService clinicServicePricingService;

    @MockBean
    private ClinicReasonsService clinicReasonsService;

    private List<Clinic> clinics;
    private List<Veterinarian> veterinarians;
    private List<ClinicReasons> clinicReasons;
    private List<ClinicServicePricing> servicePricings;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        clinics = Arrays.asList(new Clinic(), new Clinic());
        veterinarians = Arrays.asList(new Veterinarian(), new Veterinarian());
        clinicReasons = Arrays.asList(new ClinicReasons(), new ClinicReasons());
        servicePricings = Arrays.asList(new ClinicServicePricing(), new ClinicServicePricing());

        Mockito.when(clinicService.getAllClinics()).thenReturn(clinics);
        Mockito.when(veterinarianService.getAllVeterinarians()).thenReturn(veterinarians);
        Mockito.when(clinicReasonsService.getAllClinicReasons()).thenReturn(clinicReasons);
        Mockito.when(clinicServicePricingService.getAll()).thenReturn(servicePricings);
    }

    @Test
    public void testClinics() throws Exception {
        mockMvc.perform(get("/clinics"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("clinics"))
                .andExpect(model().attribute("clinics", clinics))
                .andExpect(model().attributeExists("veterinarians"))
                .andExpect(model().attribute("veterinarians", veterinarians))
                .andExpect(model().attributeExists("clinicReasons"))
                .andExpect(model().attribute("clinicReasons", clinicReasons))
                .andExpect(model().attributeExists("servicePricings"))
                .andExpect(model().attribute("servicePricings", servicePricings))
                .andExpect(view().name("Clinics"));
    }
}
