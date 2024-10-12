package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.TreatmentPlan;
import au.edu.rmit.sept.webapp.models.User;
import au.edu.rmit.sept.webapp.repositories.TreatmentPlanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TreatmentPlanServiceImplTest {

    private TreatmentPlanService treatmentPlanService;
    private TreatmentPlanRepository treatmentPlanRepository;

    @BeforeEach
    public void setUp() {
        treatmentPlanRepository = Mockito.mock(TreatmentPlanRepository.class);
        treatmentPlanService = new TreatmentPlanServiceImpl(treatmentPlanRepository);
    }

    @Test
    public void getTreatmentPlanByPetId_shouldReturnPlans() {
        TreatmentPlan treatmentPlan = new TreatmentPlan(1L, "Diagnosis", "Surgery", "Description", "Stable", true,
                LocalDate.of(2023, 8, 15), null, "Medications", "Duration", "Next steps", "Dr. Smith",
                LocalDate.of(2023, 11, 15), 150.00, "Outcome", "Notes", "Clinic", true, "Insurance", "Paid", null, null);
        when(treatmentPlanRepository.findByPetId(1L)).thenReturn(List.of(treatmentPlan));

        List<TreatmentPlan> treatmentPlans = treatmentPlanService.getTreatmentPlanByPetId(1L);
        assertEquals(1, treatmentPlans.size());
        assertEquals("Diagnosis", treatmentPlans.get(0).getDiagnosis());
        verify(treatmentPlanRepository, times(1)).findByPetId(1L);
    }

    @Test
    public void createTreatmentPlan_shouldCallRepository() {
        TreatmentPlan treatmentPlan = new TreatmentPlan(1L, "Diagnosis", "Surgery", "Description", "Stable", true,
                LocalDate.of(2023, 8, 15), null, "Medications", "Duration", "Next steps", "Dr. Smith",
                LocalDate.of(2023, 11, 15), 150.00, "Outcome", "Notes", "Clinic", true, "Insurance", "Paid", null, null);

        treatmentPlanService.createTreatmentPlan(treatmentPlan);
        verify(treatmentPlanRepository, times(1)).addTreatmentPlan(treatmentPlan);
    }

    @Test
    public void getAllTreatmentPlans_shouldReturnAllPlans() {
        TreatmentPlan treatmentPlan1 = new TreatmentPlan(1L, "Diagnosis 1", "Surgery", "Description", "Stable", true,
                LocalDate.of(2023, 8, 15), null, "Medications", "Duration", "Next steps", "Dr. Smith",
                LocalDate.of(2023, 11, 15), 150.00, "Outcome", "Notes", "Clinic", true, "Insurance", "Paid", null, null);

        TreatmentPlan treatmentPlan2 = new TreatmentPlan(2L, "Diagnosis 2", "Checkup", "Description", "Healthy", false,
                LocalDate.of(2023, 9, 01), null, "None", "3 months", "Next checkup", "Dr. Carter",
                LocalDate.of(2023, 12, 01), 90.00, "Outcome", "Notes", "Clinic", false, "None", "Paid", null, null);

        when(treatmentPlanRepository.findAll()).thenReturn(List.of(treatmentPlan1, treatmentPlan2));

        List<TreatmentPlan> treatmentPlans = treatmentPlanService.getAllTreatmentPlans();
        assertEquals(2, treatmentPlans.size());
        verify(treatmentPlanRepository, times(1)).findAll();
    }

    @Test
    public void getTreatmentPlanByPetId_shouldReturnEmptyListWhenNoPlansExist() {
        when(treatmentPlanRepository.findByPetId(1L)).thenReturn(List.of());

        List<TreatmentPlan> treatmentPlans = treatmentPlanService.getTreatmentPlanByPetId(1L);
        assertEquals(0, treatmentPlans.size());
        verify(treatmentPlanRepository, times(1)).findByPetId(1L);
    }


    @Test
    public void updateTreatmentPlan_nonVetUserShouldNotUpdatePet() {
        TreatmentPlan treatmentPlan = new TreatmentPlan(1L, "Diagnosis", "Surgery", "Description", "Stable", true,
                LocalDate.of(2023, 8, 15), null, "Medications", "Duration", "Next steps", "Dr. Smith",
                LocalDate.of(2023, 11, 15), 150.00, "Outcome", "Notes", "Clinic", true, "Insurance", "Paid", null, null);

        User nonVetUser = new User();
        nonVetUser.setUserType("User");

        treatmentPlanService.updateTreatmentPlan(treatmentPlan, nonVetUser);
        assertEquals(null, treatmentPlan.getPet()); // pet should be null for non-vet users

        verify(treatmentPlanRepository, times(1)).updateTreatmentPlan(treatmentPlan);
    }

    @Test
    public void updateTreatmentPlan_vetUserShouldUpdatePet() {
        TreatmentPlan treatmentPlan = new TreatmentPlan(1L, "Diagnosis", "Surgery", "Description", "Stable", true,
                LocalDate.of(2023, 8, 15), null, "Medications", "Duration", "Next steps", "Dr. Smith",
                LocalDate.of(2023, 11, 15), 150.00, "Outcome", "Notes", "Clinic", true, "Insurance", "Paid", null, null);

        User vetUser = new User();
        vetUser.setUserType("Vet");

        treatmentPlanService.updateTreatmentPlan(treatmentPlan, vetUser);
        assertNull(treatmentPlan.getPet());

        verify(treatmentPlanRepository, times(1)).updateTreatmentPlan(treatmentPlan);
    }

    @Test
    public void createTreatmentPlan_shouldAllowNullValuesForOptionalFields() {
        TreatmentPlan treatmentPlan = new TreatmentPlan(1L, "Diagnosis", "Surgery", "Description", "Stable", true,
                LocalDate.of(2023, 8, 15), null, "Medications", "Duration", "Next steps", "Dr. Smith",
                LocalDate.of(2023, 11, 15), 150.00, null, "Notes", "Clinic", true, null, "Paid", null, null);

        treatmentPlanService.createTreatmentPlan(treatmentPlan);
        verify(treatmentPlanRepository, times(1)).addTreatmentPlan(treatmentPlan);
    }




}

