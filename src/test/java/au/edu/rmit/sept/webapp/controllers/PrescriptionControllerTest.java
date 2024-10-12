package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.models.PetInformation;
import au.edu.rmit.sept.webapp.models.Prescription;
import au.edu.rmit.sept.webapp.models.PrescriptionRefillRequest;
import au.edu.rmit.sept.webapp.services.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class PrescriptionControllerTest {


    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private PrescriptionService prescriptionService;

    @MockBean
    private PetInformationService petInformationService;

    @MockBean
    private UserService userService;

    @MockBean
    private PrescriptionRefillService prescriptionRefillService;

    @MockBean
    private AppointmentService appointmentService;

    private PetInformation pet;
    private Prescription prescription;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        pet = new PetInformation(1L, "Buddy", 3, "Male", 25.5, "Golden Retriever", null, 1L);
        prescription = new Prescription(
                1L,
                pet,
                "AllerPet 10mg",
                "1 tablet daily",
                LocalDate.parse("2025-10-15"),
                30,
                0,
                LocalDate.parse("2026-10-15"),
                "Take with food"
        );
    }

    @Test
    public void testViewPrescriptionPage_UserNotLoggedIn() throws Exception {
        mockMvc.perform(get("/view-prescription"))
                .andExpect(status().isOk())
                .andExpect(view().name("viewPrescription"))
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(model().attribute("errorMessage", "You must be logged in to view prescriptions."));
    }

    @Test
    public void testViewPrescriptionPage_UserLoggedIn() throws Exception {
        when(petInformationService.getPetByUserId(1L)).thenReturn(List.of(pet));

        mockMvc.perform(get("/view-prescription").sessionAttr("userId", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("viewPrescription"))
                .andExpect(model().attributeExists("pets"))
                .andExpect(model().attribute("pets", List.of(pet)));
    }

    @Test
    public void testViewPrescription_PrescriptionsFound() throws Exception {
        when(petInformationService.getPetById(1L)).thenReturn(pet);
        when(prescriptionService.getPrescriptionsByPetId(1L)).thenReturn(List.of(prescription));

        mockMvc.perform(get("/prescription?petId=1").sessionAttr("userId", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("viewPrescription"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attributeExists("prescriptions"))
                .andExpect(model().attribute("pet", pet))
                .andExpect(model().attribute("prescriptions", List.of(prescription)));
    }

    @Test
    public void testViewPrescription_PetNotFound() throws Exception {
        when(petInformationService.getPetById(999L)).thenReturn(null);

        mockMvc.perform(get("/prescription?petId=999").sessionAttr("userId", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("viewPrescription"))
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(model().attribute("errorMessage", "No pets found for the logged-in user."));
    }

    @Test
    public void testOrderPrescriptionPage_PetNotOwnedByUser() throws Exception {
        PetInformation anotherPet = new PetInformation(2L, "Max", 5, "Male", 15.0, "Beagle", null, 2L);
        when(petInformationService.getPetById(2L)).thenReturn(anotherPet);

        mockMvc.perform(get("/prescription/order?petId=2").sessionAttr("userId", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("refillPrescription"))
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(model().attribute("errorMessage", "Pet not found or you do not have permission to order prescriptions for this pet."));
    }

    @Test
    public void testSubmitRefillRequest_Success() throws Exception {

        PrescriptionRefillRequest refillRequest = new PrescriptionRefillRequest(
                1L,
                1L,
                List.of("AllerPet"),
                "123 Main St",
                "Sample City",
                "Sample State",
                "12345",
                "Please handle with care",
                LocalDateTime.now()
        );

        mockMvc.perform(post("/prescription/refill")
                        .sessionAttr("userId", 1L)
                        .flashAttr("refillRequest", refillRequest))
                .andExpect(status().isOk())
                .andExpect(view().name("refillPrescription"))
                .andExpect(model().attributeExists("successMessage"))
                .andExpect(model().attribute("successMessage", "Your prescription refill request has been successfully submitted."));
    }



    @Test
    public void testGetRefillOrders_NoOrdersFound() throws Exception {
        when(petInformationService.getPetByUserId(1L)).thenReturn(List.of(pet));
        when(prescriptionRefillService.getAllRefillRequests()).thenReturn(List.of());

        mockMvc.perform(get("/prescription/orderdetails").sessionAttr("userId", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("orderPrescription"))
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(model().attribute("errorMessage", "No orders found for your pets."));
    }
}
