package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.models.*;
import au.edu.rmit.sept.webapp.services.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MedicalRecordController.class)
public class MedicalRecordControllerTest {

    @Autowired
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
    private UserService userService;

    private PetInformation pet;
    private User owner;
    private MedicalHistory medicalHistory;
    private VaccinationRecord vaccinationRecord;
    private TreatmentPlan treatmentPlan;

    @BeforeEach
    public void setUp() {
        pet = new PetInformation(1L, "Max", 3, "Male", 5.0, "Labrador", null, 1L);
        owner = new User(1L, "Mark Smith", "marksmith123@example.com", "Mark@123", 1234567890L, "11 Swanston St, Melbourne CBD, 3000");
        medicalHistory = new MedicalHistory(1L, null, "Diagnosis", "Treatment", "Medication", "Condition", null);
        vaccinationRecord = new VaccinationRecord(1L, 1L, "Rabies", null, null, "Yes", null, "Dr. Smith", "Clinic", "Completed", "All good");
        treatmentPlan = new TreatmentPlan(1L, "Surgery", "Knee Surgery", "Details", "Recovering", true, null, null, "Painkillers", "2 weeks", "Follow-up", "Dr. Smith", null, 150.0, "Successful", "Notes", "Clinic", true, "Insurance", "Paid", null, null);
    }

    @Test
    public void testAccessMedicalRecords_ShouldReturnView() throws Exception {
        when(petInformationService.getPetByUserId(1L)).thenReturn(List.of(pet));

        MockHttpServletRequestBuilder requestBuilder = get("/access-medical-records")
                .sessionAttr("userId", 1L);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(view().name("AccessMedicalRecords"))
                .andExpect(model().attributeExists("pets"))
                .andExpect(model().attribute("pets", List.of(pet)));
    }

    @Test
    public void testViewMedicalRecord_ShouldReturnMedicalRecords() throws Exception {
        when(petInformationService.getPetById(1L)).thenReturn(pet);
        when(userService.findByUser(1L)).thenReturn(owner);
        when(medicalHistoryService.getMedicalHistoryByPetId(1L)).thenReturn(List.of(medicalHistory));
        when(vaccinationRecordService.getVaccinationRecordByPetId(1L)).thenReturn(List.of(vaccinationRecord));
        when(treatmentPlanService.getTreatmentPlanByPetId(1L)).thenReturn(List.of(treatmentPlan));

        MockHttpServletRequestBuilder requestBuilder = get("/viewMedicalRecord?petId=1")
                .sessionAttr("userId", 1L);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(view().name("AccessMedicalRecords"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attributeExists("medicalHistory"))
                .andExpect(model().attributeExists("vaccinationRecords"))
                .andExpect(model().attributeExists("treatmentPlans"))
                .andExpect(model().attribute("pet", pet))
                .andExpect(model().attribute("medicalHistory", List.of(medicalHistory)))
                .andExpect(model().attribute("vaccinationRecords", List.of(vaccinationRecord)))
                .andExpect(model().attribute("treatmentPlans", List.of(treatmentPlan)));
    }

    @Test
    public void testViewMedicalRecord_PetNotFound_ShouldReturnErrorMessage() throws Exception {
        when(petInformationService.getPetById(999L)).thenReturn(null);

        MockHttpServletRequestBuilder requestBuilder = get("/viewMedicalRecord?petId=999")
                .sessionAttr("userId", 1L);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(view().name("AccessMedicalRecords"))
                .andExpect(model().attributeExists("errorMessage"));
    }

    @Test
    public void testShowVaccinationDetails_ShouldReturnVaccinationRecords() throws Exception {
        when(petInformationService.getPetById(1L)).thenReturn(pet);
        when(vaccinationRecordService.getVaccinationRecordByPetId(1L)).thenReturn(List.of(vaccinationRecord));

        MockHttpServletRequestBuilder requestBuilder = get("/vaccination?petId=1")
                .sessionAttr("userId", 1L);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(view().name("ViewVaccinationRecords"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attributeExists("vaccinationRecords"))
                .andExpect(model().attribute("vaccinationRecords", List.of(vaccinationRecord)));
    }

    @Test
    public void testShowVaccinationDetails_NoRecords_ShouldReturnErrorMessage() throws Exception {
        when(petInformationService.getPetById(1L)).thenReturn(pet);
        when(vaccinationRecordService.getVaccinationRecordByPetId(1L)).thenReturn(Collections.emptyList());

        MockHttpServletRequestBuilder requestBuilder = get("/vaccination?petId=1")
                .sessionAttr("userId", 1L);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(view().name("ViewVaccinationRecords"))
                .andExpect(model().attributeExists("errorMessage"));
    }

    @Test
    public void testShowTreatmentPlanDetails_ShouldReturnTreatmentPlans() throws Exception {
        when(petInformationService.getPetById(1L)).thenReturn(pet);
        when(treatmentPlanService.getTreatmentPlanByPetId(1L)).thenReturn(List.of(treatmentPlan));

        MockHttpServletRequestBuilder requestBuilder = get("/vtreatmentPlan?petId=1")
                .sessionAttr("userId", 1L);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(view().name("ViewTreatmentPlan"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attributeExists("treatmentPlans"))
                .andExpect(model().attribute("treatmentPlans", List.of(treatmentPlan)));
    }

    @Test
    public void testShowTreatmentPlanDetails_NoPlans_ShouldReturnErrorMessage() throws Exception {
        when(petInformationService.getPetById(1L)).thenReturn(pet);
        when(treatmentPlanService.getTreatmentPlanByPetId(1L)).thenReturn(Collections.emptyList());

        MockHttpServletRequestBuilder requestBuilder = get("/vtreatmentPlan?petId=1")
                .sessionAttr("userId", 1L);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(view().name("ViewTreatmentPlan"))
                .andExpect(model().attributeExists("errorMessage"));
    }
}
