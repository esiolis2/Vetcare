package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.models.PetInformation;
import au.edu.rmit.sept.webapp.models.TreatmentPlan;
import au.edu.rmit.sept.webapp.models.User;
import au.edu.rmit.sept.webapp.services.AppointmentService;
import au.edu.rmit.sept.webapp.services.PetInformationService;
import au.edu.rmit.sept.webapp.services.TreatmentPlanService;
import au.edu.rmit.sept.webapp.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
@ActiveProfiles("test")
public class TreatmentPlanControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private TreatmentPlanService treatmentPlanService;

    @MockBean
    private PetInformationService petInformationService;

    @MockBean
    private UserService userService;

    @MockBean
    private AppointmentService appointmentService;

    private PetInformation pet;
    private User user;
    private TreatmentPlan treatmentPlan;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        user = new User(1L, "John Doe", "johndoe@example.com", "password123", 123456789L, "123 Street, City", "User");
        pet = new PetInformation(1L, "Buddy", 3, "Male", 25.5, "Golden Retriever", null, 1L);
        treatmentPlan = new TreatmentPlan(1L, "Diagnosis", "Surgery", "Details", "Healthy", true, null, null, "Meds", "Duration", "Next steps", "Vet Name", null, 200.0, "Success", "Notes", "Clinic", true, "Insurance", "Paid", null, null);
    }

    @Test
    public void testViewTreatmentPlanPage_UserNotLoggedIn() throws Exception {
        mockMvc.perform(get("/view-treatment-plan"))
                .andExpect(status().isOk())
                .andExpect(view().name("ViewTreatmentPlan"))
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(model().attribute("errorMessage", "No treatment plans available for the user."));
    }

    @Test
    public void testViewTreatmentPlanPage_UserLoggedInWithNoPets() throws Exception {
        when(petInformationService.getPetByUserId(1L)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/view-treatment-plan").sessionAttr("userId", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("ViewTreatmentPlan"))
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(model().attribute("errorMessage", "No treatment plans found for the logged-in user."));
    }

    @Test
    public void testViewTreatmentPlanPage_UserLoggedInWithPets() throws Exception {
        when(petInformationService.getPetByUserId(1L)).thenReturn(List.of(pet));

        mockMvc.perform(get("/view-treatment-plan").sessionAttr("userId", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("ViewTreatmentPlan"))
                .andExpect(model().attributeExists("pets"))
                .andExpect(model().attribute("pets", List.of(pet)));
    }

    @Test
    public void testSelectUser_NoPetsFound() throws Exception {
        when(petInformationService.getPetByUserId(1L)).thenReturn(Collections.emptyList());
        when(userService.findByUser(1L)).thenReturn(user);

        mockMvc.perform(get("/treatment/selectUser?userId=1"))
                .andExpect(status().isOk())
                .andExpect(view().name("treatmentForm"))
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(model().attribute("errorMessage", "No pets found for this user."));
    }


    @Test
    public void testViewTreatmentPlan_PetFound() throws Exception {
        when(petInformationService.getPetById(1L)).thenReturn(pet);
        when(treatmentPlanService.getTreatmentPlanByPetId(1L)).thenReturn(List.of(treatmentPlan));

        mockMvc.perform(get("/treatmentPlan?petId=1").sessionAttr("userId", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("ViewTreatmentPlan"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attributeExists("treatmentPlans"))
                .andExpect(model().attribute("pet", pet))
                .andExpect(model().attribute("treatmentPlans", List.of(treatmentPlan)));
    }

    @Test
    public void testViewTreatmentPlan_PetNotFound() throws Exception {
        when(petInformationService.getPetById(999L)).thenReturn(null);

        mockMvc.perform(get("/treatmentPlan?petId=999").sessionAttr("userId", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("ViewTreatmentPlan"))
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(model().attribute("errorMessage", "No pets found for the logged-in user."));
    }

    @Test
    public void testEditTreatmentPlanForm_UserIsVet() throws Exception {
        when(userService.getAllUsers()).thenReturn(List.of(user));

        mockMvc.perform(get("/edit-treatment-plan").sessionAttr("userId", 1L).sessionAttr("userType", "Vet"))
                .andExpect(status().isOk())
                .andExpect(view().name("treatmentForm"))
                .andExpect(model().attributeExists("canEditPet"))
                .andExpect(model().attribute("canEditPet", true));
    }

    @Test
    public void testEditTreatmentPlanForm_UserIsNotVet() throws Exception {
        when(userService.getAllUsers()).thenReturn(List.of(user));

        mockMvc.perform(get("/edit-treatment-plan").sessionAttr("userId", 1L).sessionAttr("userType", "User"))
                .andExpect(status().isOk())
                .andExpect(view().name("treatmentForm"))
                .andExpect(model().attributeExists("canEditPet"))
                .andExpect(model().attribute("canEditPet", false));
    }


    @Test
    public void testSaveTreatmentPlan_CreateNewRecord() throws Exception {
        when(petInformationService.getPetById(1L)).thenReturn(pet);
        when(treatmentPlanService.getTreatmentPlanByPetId(1L)).thenReturn(Collections.emptyList());

        mockMvc.perform(post("/save-treatment-plan")
                        .sessionAttr("loggedInUser", user)
                        .param("petId", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("HomePage"))
                .andExpect(model().attributeExists("successMessage"))
                .andExpect(model().attribute("successMessage", "New treatment plan created successfully."));
    }

    @Test
    public void testSaveTreatmentPlan_UpdateExistingRecord() throws Exception {
        when(petInformationService.getPetById(1L)).thenReturn(pet);
        when(treatmentPlanService.getTreatmentPlanByPetId(1L)).thenReturn(List.of(treatmentPlan));

        mockMvc.perform(post("/save-treatment-plan")
                        .sessionAttr("loggedInUser", user)
                        .param("petId", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("HomePage"))
                .andExpect(model().attributeExists("successMessage"))
                .andExpect(model().attribute("successMessage", "Treatment plan updated successfully."));
    }

    @Test
    public void testViewTreatmentPlan_NoTreatmentPlansFound() throws Exception {
        when(petInformationService.getPetById(1L)).thenReturn(pet);
        when(treatmentPlanService.getTreatmentPlanByPetId(1L)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/treatmentPlan?petId=1").sessionAttr("userId", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("ViewTreatmentPlan"))
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(model().attribute("errorMessage", "No pets found for the logged-in user."));
    }

}


