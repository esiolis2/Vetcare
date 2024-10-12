package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.models.MedicalHistory;
import au.edu.rmit.sept.webapp.models.PetInformation;
import au.edu.rmit.sept.webapp.models.User;
import au.edu.rmit.sept.webapp.services.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class MedicalRecordControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private PetInformationService petInformationService;

    @MockBean
    private MedicalHistoryService medicalHistoryService;

    @MockBean
    private VaccinationRecordService vaccinationRecordService;

    @MockBean
    private TreatmentPlanService treatmentPlanService;

    @MockBean
    private PrescriptionService prescriptionService;

    @MockBean
    private UserService userService;

    @MockBean
    private AppointmentService appointmentService;

    private PetInformation pet;
    private User user;
    private MedicalHistory medicalHistory;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        user = new User(1L, "John Doe", "johndoe@example.com", "password123", 123456789L, "123 Street, City", "User");
        pet = new PetInformation(1L, "Buddy", 3, "Male", 25.5, "Golden Retriever", null, 1L);
        medicalHistory = new MedicalHistory(1L, LocalDate.parse("2024-10-10"), "No issues", "None", "No medications", "None", LocalDate.parse("2025-10-10"));
        medicalHistory.setPet(pet);
    }

    @Test
    public void testAccessMedicalRecords_UserHasNoPets() throws Exception {
        when(petInformationService.getPetByUserId(1L)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/access-medical-records").sessionAttr("userId", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("AccessMedicalRecords"))
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(model().attribute("errorMessage", "No medical records found for the logged-in user."));
    }

    @Test
    public void testAccessMedicalRecords_UserHasPets() throws Exception {
        when(petInformationService.getPetByUserId(1L)).thenReturn(List.of(pet));

        mockMvc.perform(get("/access-medical-records").sessionAttr("userId", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("AccessMedicalRecords"))
                .andExpect(model().attributeExists("pets"))
                .andExpect(model().attribute("pets", List.of(pet)));
    }

    @Test
    public void testViewMedicalRecord_PetFound() throws Exception {
        when(petInformationService.getPetById(1L)).thenReturn(pet);
        when(userService.findByUser(1L)).thenReturn(user);
        when(medicalHistoryService.getMedicalHistoryByPetId(1L)).thenReturn(List.of(medicalHistory));
        when(vaccinationRecordService.getVaccinationRecordByPetId(1L)).thenReturn(Collections.emptyList());
        when(treatmentPlanService.getTreatmentPlanByPetId(1L)).thenReturn(Collections.emptyList());
        when(prescriptionService.getPrescriptionsByPetId(1L)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/viewMedicalRecord?petId=1"))
                .andExpect(status().isOk())
                .andExpect(view().name("AccessMedicalRecords"))
                .andExpect(model().attributeExists("medicalHistory"))
                .andExpect(model().attribute("medicalHistory", List.of(medicalHistory)))
                .andExpect(model().attributeExists("vaccinationMessage"))
                .andExpect(model().attribute("vaccinationMessage", "No vaccination records available."));
    }

    @Test
    public void testViewMedicalRecord_PetNotFound() throws Exception {
        when(petInformationService.getPetById(999L)).thenReturn(null);

        mockMvc.perform(get("/viewMedicalRecord?petId=999"))
                .andExpect(status().isOk())
                .andExpect(view().name("AccessMedicalRecords"))
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(model().attribute("errorMessage", "No pets found for the logged-in user."));
    }

    @Test
    public void testEditMedicalRecordForm_UserIsVet() throws Exception {
        when(userService.findByUser(1L)).thenReturn(user);
        when(petInformationService.getPetByUserId(1L)).thenReturn(List.of(pet));

        mockMvc.perform(get("/edit-medical-record").sessionAttr("userId", 1L).sessionAttr("userType", "Vet"))
                .andExpect(status().isOk())
                .andExpect(view().name("medicalRecordForm"))
                .andExpect(model().attributeExists("canEditPet"))
                .andExpect(model().attribute("canEditPet", true));
    }

    @Test
    public void testSaveMedicalRecord_CreateNewRecord() throws Exception {
        when(petInformationService.getPetById(1L)).thenReturn(pet);
        when(medicalHistoryService.getMedicalHistoryByPetId(1L)).thenReturn(Collections.emptyList());

        mockMvc.perform(post("/save-medical-record")
                        .sessionAttr("loggedInUser", user)
                        .param("petId", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("HomePage"))
                .andExpect(model().attributeExists("successMessage"))
                .andExpect(model().attribute("successMessage", "New medical record created successfully."));
    }

    @Test
    public void testSaveMedicalRecord_UpdateExistingRecord() throws Exception {
        when(petInformationService.getPetById(1L)).thenReturn(pet);
        when(medicalHistoryService.getMedicalHistoryByPetId(1L)).thenReturn(List.of(medicalHistory));

        mockMvc.perform(post("/save-medical-record")
                        .sessionAttr("loggedInUser", user)
                        .param("petId", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("HomePage"));
    }
}
