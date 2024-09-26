//package au.edu.rmit.sept.webapp.controllers;
//
//import au.edu.rmit.sept.webapp.models.PetInformation;
//import au.edu.rmit.sept.webapp.models.TreatmentPlan;
//import au.edu.rmit.sept.webapp.services.PetInformationService;
//import au.edu.rmit.sept.webapp.services.TreatmentPlanService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.List;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(TreatmentPlanController.class)
//public class TreatmentPlanControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private TreatmentPlanService treatmentPlanService;
//
//    @MockBean
//    private PetInformationService petInformationService;
//
//    private PetInformation pet;
//    private TreatmentPlan treatmentPlan;
//
//    @BeforeEach
//    public void setUp() {
//        pet = new PetInformation(1L, "Max", 3, "Male", 5.0, "Labrador", null, "Mark Smith", "123456789");
//        treatmentPlan = new TreatmentPlan(1L, "Diagnosis", "Surgery", "Details", "Healthy", true, null, null, "Meds", "Duration", "Next steps", "Vet Name", null, 200.0, "Success", "Notes", "Clinic", true, "Insurance", "Paid", null, null);
//    }
//
//    @Test
//    public void testViewTreatmentPlan_ShouldReturnTreatmentPlan() throws Exception {
//        when(petInformationService.getPetById(1L)).thenReturn(pet);
//        when(treatmentPlanService.getTreatmentPlanByPetId(1L)).thenReturn(List.of(treatmentPlan));
//
//        mockMvc.perform(get("/treatmentPlan?petId=1"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("ViewTreatmentPlan"))
//                .andExpect(model().attributeExists("pet"))
//                .andExpect(model().attributeExists("treatmentPlans"))
//                .andExpect(model().attribute("pet", pet))
//                .andExpect(model().attribute("treatmentPlans", List.of(treatmentPlan)));
//    }
//
//    @Test
//    public void testViewTreatmentPlan_PetNotFound_ShouldReturnErrorMessage() throws Exception {
//        when(petInformationService.getPetById(999L)).thenReturn(null);
//
//        mockMvc.perform(get("/treatmentPlan?petId=999"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("ViewTreatmentPlan"))
//                .andExpect(model().attributeExists("errorMessage"))
//                .andExpect(model().attribute("errorMessage", "Pet not found."));
//    }
//
//    @Test
//    public void testViewTreatmentPlan_NoTreatmentPlans_ShouldReturnErrorMessage() throws Exception {
//        when(petInformationService.getPetById(1L)).thenReturn(pet);
//        when(treatmentPlanService.getTreatmentPlanByPetId(1L)).thenReturn(List.of());
//
//        mockMvc.perform(get("/treatmentPlan?petId=1"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("ViewTreatmentPlan"))
//                .andExpect(model().attributeExists("errorMessage"))
//                .andExpect(model().attribute("errorMessage", "No treatment plans found for this pet."));
//    }
//}
