package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.models.PetInformation;
import au.edu.rmit.sept.webapp.models.User;
import au.edu.rmit.sept.webapp.models.VaccinationRecord;
import au.edu.rmit.sept.webapp.services.AppointmentService;
import au.edu.rmit.sept.webapp.services.PetInformationService;
import au.edu.rmit.sept.webapp.services.UserService;
import au.edu.rmit.sept.webapp.services.VaccinationRecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class VaccinationRecordControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private PetInformationService petInformationService;

    @MockBean
    private AppointmentService appointmentService;

    @MockBean
    private VaccinationRecordService vaccinationRecordService;

    @MockBean
    private UserService userService;

    private PetInformation pet;
    private User user;
    private VaccinationRecord vaccinationRecord;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        user = new User(1L, "John Doe", "johndoe@example.com", "password123", 123456789L, "123 Street, City", "User");
        pet = new PetInformation(1L, "Buddy", 3, "Male", 25.5, "Golden Retriever", null, 1L);
        vaccinationRecord = new VaccinationRecord(
                1L,
                pet.getPetID(),
                "Rabies",
                LocalDate.parse("2023-10-15"),
                LocalDate.parse("2024-10-15"),
                "Yes",
                new BigDecimal("2.5"),
                "Dr. Smith",
                "Healthy Pets Clinic",
                "Completed",
                "No side effects noted"
        );   }

    @Test
    public void testViewVaccinationRecordsPage_UserNotLoggedIn() throws Exception {
        mockMvc.perform(get("/view-vaccination-records"))
                .andExpect(status().isOk())
                .andExpect(view().name("ViewVaccinationRecords"))
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(model().attribute("errorMessage", "No vaccination records available for the user."));
    }

    @Test
    public void testViewVaccinationRecordsPage_UserLoggedInWithNoPets() throws Exception {
        when(petInformationService.getPetByUserId(1L)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/view-vaccination-records").sessionAttr("userId", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("ViewVaccinationRecords"))
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(model().attribute("errorMessage", "No vaccination records found for the logged-in user."));
    }

    @Test
    public void testViewVaccinationRecordsPage_UserLoggedInWithPets() throws Exception {
        when(petInformationService.getPetByUserId(1L)).thenReturn(List.of(pet));

        mockMvc.perform(get("/view-vaccination-records").sessionAttr("userId", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("ViewVaccinationRecords"))
                .andExpect(model().attributeExists("pets"))
                .andExpect(model().attribute("pets", List.of(pet)));
    }

    @Test
    public void testShowVaccinationDetails_PetFound() throws Exception {
        when(petInformationService.getPetById(1L)).thenReturn(pet);
        when(vaccinationRecordService.getVaccinationRecordByPetId(1L)).thenReturn(List.of(vaccinationRecord));

        mockMvc.perform(get("/vaccination-record-details?petId=1").sessionAttr("userId", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("ViewVaccinationRecords"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attributeExists("vaccinationRecords"))
                .andExpect(model().attribute("pet", pet))
                .andExpect(model().attribute("vaccinationRecords", List.of(vaccinationRecord)));
    }

    @Test
    public void testShowVaccinationDetails_PetNotFound() throws Exception {
        when(petInformationService.getPetById(999L)).thenReturn(null);

        mockMvc.perform(get("/vaccination-record-details?petId=999").sessionAttr("userId", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("ViewVaccinationRecords"))
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(model().attribute("errorMessage", "No pets found for the logged-in user."));
    }

    @Test
    public void testEditVaccinationRecordForm_UserIsVet() throws Exception {
        when(userService.getAllUsers()).thenReturn(List.of(user));
        when(petInformationService.getPetByUserId(1L)).thenReturn(List.of(pet));

        mockMvc.perform(get("/edit-vaccination-record").sessionAttr("userId", 1L).sessionAttr("userType", "Vet"))
                .andExpect(status().isOk())
                .andExpect(view().name("VaccinationForm"))
                .andExpect(model().attributeExists("canEditPet"))
                .andExpect(model().attribute("canEditPet", true));
    }

    @Test
    public void testSaveVaccinationRecord_CreateNewRecord() throws Exception {
        when(petInformationService.getPetById(1L)).thenReturn(pet);

        mockMvc.perform(post("/save-vaccination")
                        .sessionAttr("loggedInUser", user)
                        .param("petId", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("HomePage"))
                .andExpect(model().attributeExists("successMessage"))
                .andExpect(model().attribute("successMessage", "Vaccination record saved successfully."));
    }

    @Test
    public void testShowVaccinationDetails_NotAuthorized() throws Exception {
        PetInformation anotherPet = new PetInformation(2L, "Max", 5, "Male", 20.0, "Beagle", null, 2L);
        when(petInformationService.getPetById(2L)).thenReturn(anotherPet);

        mockMvc.perform(get("/vaccination-record-details?petId=2").sessionAttr("userId", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("ViewVaccinationRecords"))
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(model().attribute("errorMessage", "No pets found for the logged-in user."));
    }

    @Test
    public void testEditVaccinationRecordForm_UserIsNotVet() throws Exception {
        when(userService.getAllUsers()).thenReturn(List.of(user));
        when(petInformationService.getPetByUserId(1L)).thenReturn(List.of(pet));

        mockMvc.perform(get("/edit-vaccination-record").sessionAttr("userId", 1L).sessionAttr("userType", "User"))
                .andExpect(status().isOk())
                .andExpect(view().name("VaccinationForm"))
                .andExpect(model().attributeExists("canEditPet"))
                .andExpect(model().attribute("canEditPet", false));
    }

    @Test
    public void testSaveVaccination_PetNotFound() throws Exception {
        when(petInformationService.getPetById(999L)).thenReturn(null);

        mockMvc.perform(post("/save-vaccination")
                        .sessionAttr("loggedInUser", user)
                        .param("petId", "999"))
                .andExpect(status().isOk())
                .andExpect(view().name("VaccinationForm"))
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(model().attribute("errorMessage", "Pet not found."));
    }



}
