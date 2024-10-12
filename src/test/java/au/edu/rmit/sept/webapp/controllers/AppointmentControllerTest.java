package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.models.*;
import au.edu.rmit.sept.webapp.services.*;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@SpringBootTest
public class AppointmentControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private AppointmentService appointmentService;

    @MockBean
    private ClinicService clinicService;

    @MockBean
    private VeterinarianService veterinarianService;

    @MockBean
    private ClinicServicePricingService clinicServicePricingService;

    @MockBean
    private ClinicReasonsService clinicReasonsService;

    @MockBean
    private PetInformationService petInformationService;

    @MockBean
    private UserService userService;

    private List<Clinic> clinics;
    private List<Veterinarian> veterinarians;
    private List<ClinicReasons> clinicReasons;
    private List<ClinicServicePricing> servicePricings;
    private List<PetInformation> pets;
    private List<Appointment> appointments;
    private User user;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();


        user = new User();
        user.setId(1L);
        user.setEmail("testUser@test.com");

        Mockito.when(userService.findByEmail("testUser@test.com")).thenReturn(user);

        clinics = Arrays.asList(new Clinic(), new Clinic());
        veterinarians = Arrays.asList(new Veterinarian(), new Veterinarian());
        clinicReasons = Arrays.asList(new ClinicReasons(), new ClinicReasons());
        servicePricings = Arrays.asList(new ClinicServicePricing(), new ClinicServicePricing());
        PetInformation pet = new PetInformation();
        pet.setPetID(1L);
        pets = Arrays.asList(pet);
        appointments = Arrays.asList(new Appointment(), new Appointment());

        Mockito.when(userService.findByEmail("testUser@test.com")).thenReturn(user);
        Mockito.when(clinicService.getAllClinics()).thenReturn(clinics);
        Mockito.when(veterinarianService.getAllVeterinarians()).thenReturn(veterinarians);
        Mockito.when(clinicReasonsService.getAllClinicReasons()).thenReturn(clinicReasons);
        Mockito.when(clinicServicePricingService.getAll()).thenReturn(servicePricings);
        Mockito.when(petInformationService.getPetByUserId(user.getId())).thenReturn(pets);

    }

    // Test for the appointment booking page
    @Test
    public void testBookApp() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.getSession().setAttribute("userEmail", user.getEmail());
        mockMvc.perform(get("/appointments").session((MockHttpSession) request.getSession()))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("clinics"))
                .andExpect(model().attribute("clinics", clinics))
                .andExpect(model().attributeExists("veterinarians"))
                .andExpect(model().attribute("veterinarians", veterinarians))
                .andExpect(model().attributeExists("clinicReasons"))
                .andExpect(model().attribute("clinicReasons", clinicReasons))
                .andExpect(model().attributeExists("servicePricings"))
                .andExpect(model().attribute("servicePricings", servicePricings))
                .andExpect(model().attributeExists("petInformation"))
                .andExpect(model().attribute("petInformation", pets))
                .andExpect(view().name("BookApp"));
    }

    // Test for creating an appointment
    @Test
    public void testCreateAppointment() throws Exception {
        mockMvc.perform(post("/add")
                        .param("appointmentDate", "2024-10-01")
                        .param("appointmentTime", "10:00")
                        .param("veterinarianId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }



    // Test for deleting an appointment
    @Test
    public void testDeleteAppointment() throws Exception {
        mockMvc.perform(post("/delete")
                        .param("id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }
}
