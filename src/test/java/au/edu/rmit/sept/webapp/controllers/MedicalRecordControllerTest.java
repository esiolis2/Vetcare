package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.models.MedicalHistory;
import au.edu.rmit.sept.webapp.models.PetInformation;
import au.edu.rmit.sept.webapp.models.TreatmentPlan;
import au.edu.rmit.sept.webapp.models.VaccinationRecord;
import au.edu.rmit.sept.webapp.services.MedicalHistoryService;
import au.edu.rmit.sept.webapp.services.PetInformationService;
import au.edu.rmit.sept.webapp.services.TreatmentPlanService;
import au.edu.rmit.sept.webapp.services.VaccinationRecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

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
    private PetInformation pet;
    private MedicalHistory medicalHistory;
    private VaccinationRecord vaccinationRecord;
    private TreatmentPlan treatmentPlan;

    @BeforeEach
    public void setUp() {
        pet = new PetInformation(1L, "Max", 3, "Male", 5.0, "Labrador", null, "Mark Smith", "123456789");
        medicalHistory = new MedicalHistory(1L, null, "Diagnosis", "Treatment", "Medication", "Condition", null);
        vaccinationRecord = new VaccinationRecord(1L, 1L, "Rabies", null, null, "Yes", null, "Dr. Smith", "Clinic", "Completed", "All good");
        treatmentPlan = new TreatmentPlan(1L, "Knee Surgery", "Surgery", "Details", "Recovering", true, null, null, "Painkillers", "Duration", "Next steps", "Dr. Smith", null, 150.0, "Outcome", "Notes", "Clinic", true, "Insurance", "Paid", null, null);

    }

    @Test
    public void testViewMedicalRecord_ShouldReturnMedicalRecord() throws Exception{
        when(petInformationService.getPetById(1L)).thenReturn(pet);
        when(medicalHistoryService.getMedicalHistoryByPetId(1L)).thenReturn(List.of(medicalHistory));

        mockMvc.perform(get("/viewMedicalRecord?petId=1"))
                .andExpect(status().isOk())
                .andExpect(view().name("AccessMedicalRecords"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attributeExists("medicalHistory"))
                .andExpect(model().attribute("pet", pet))
                .andExpect(model().attribute("medicalHistory", List.of(medicalHistory)));
    }

    @Test
    public void testViewMedicalRecord_PetNotFound_ShouldReturnErrorMessage() throws Exception {
        when(petInformationService.getPetById(999L)).thenReturn(null);

        mockMvc.perform(get("/viewMedicalRecord?petId=999"))
                .andExpect(status().isOk())
                .andExpect(view().name("AccessMedicalRecords"))
                .andExpect(model().attributeExists("errorMessage"));
    }

    @Test
    public void testShowVaccinationDetails_ShouldReturnVaccinationRecords() throws Exception {
        when(petInformationService.getPetById(1L)).thenReturn(pet);
        when(vaccinationRecordService.getVaccinationRecordByPetId(1L)).thenReturn(List.of(vaccinationRecord));

        mockMvc.perform(get("/vaccination?petId=1"))
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

        mockMvc.perform(get("/vaccination?petId=1"))
                .andExpect(status().isOk())
                .andExpect(view().name("ViewVaccinationRecords"))
                .andExpect(model().attributeExists("errorMessage"));
    }

    @Test
    public void testShowTreatmentPlanDetails_ShouldReturnTreatmentPlans() throws Exception {
        when(petInformationService.getPetById(1L)).thenReturn(pet);
        when(treatmentPlanService.getTreatmentPlanByPetId(1L)).thenReturn(List.of(treatmentPlan));

        mockMvc.perform(get("/vtreatmentPlan?petId=1"))
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

        mockMvc.perform(get("/vtreatmentPlan?petId=1"))
                .andExpect(status().isOk())
                .andExpect(view().name("ViewTreatmentPlan"))
                .andExpect(model().attributeExists("errorMessage"));
    }

    @Test
    public void testAccessMedicalRecords_ShouldReturnView() throws Exception {
        mockMvc.perform(get("/access-medical-records"))
                .andExpect(status().isOk())
                .andExpect(view().name("AccessMedicalRecords"))
                .andExpect(model().attributeExists("pets"));
    }



}
