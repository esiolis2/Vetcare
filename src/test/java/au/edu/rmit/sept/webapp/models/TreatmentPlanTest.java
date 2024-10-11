package au.edu.rmit.sept.webapp.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TreatmentPlanTest {

    private TreatmentPlan treatmentPlan;
    private PetInformation pet;

    @BeforeEach
    public void setUp() {
        pet = new PetInformation(1L, "Buddy", 3, "Male", 25.5, "Golden Retriever", null, 1L);
        treatmentPlan = new TreatmentPlan(
                1L,
                "Diagnosis A",
                "Surgery",
                "Detailed Description of Treatment",
                "Critical",
                true,
                LocalDate.parse("2023-10-15"),
                LocalDate.parse("2023-12-15"),
                "Medication A",
                "2 weeks",
                "Follow-up required",
                "Dr. Smith",
                LocalDate.parse("2024-01-15"),
                500.0,
                "Successful",
                "Notes about treatment",
                "Vet Clinic",
                true,
                "Insurance Company A",
                "Paid",
                LocalDate.parse("2023-10-01"),
                LocalDate.parse("2023-11-01")
        );
        treatmentPlan.setPet(pet);
    }

    @Test
    public void testParameterizedConstructor() {
        assertNotNull(treatmentPlan);
        assertEquals(1L, treatmentPlan.getTreatmentPlanID());
        assertEquals("Diagnosis A", treatmentPlan.getDiagnosis());
        assertEquals("Surgery", treatmentPlan.getTreatmentType());
        assertEquals("Detailed Description of Treatment", treatmentPlan.getTreatmentDescription());
        assertEquals("Critical", treatmentPlan.getPetCondition());
        assertEquals(true, treatmentPlan.getIsEmergency());
        assertEquals(LocalDate.parse("2023-10-15"), treatmentPlan.getStartDate());
        assertEquals(LocalDate.parse("2023-12-15"), treatmentPlan.getEndDate());
        assertEquals("Medication A", treatmentPlan.getPrescribedMedications());
        assertEquals("2 weeks", treatmentPlan.getTreatmentDuration());
        assertEquals("Follow-up required", treatmentPlan.getNextSteps());
        assertEquals("Dr. Smith", treatmentPlan.getVetName());
        assertEquals(LocalDate.parse("2024-01-15"), treatmentPlan.getFollowUpDate());
        assertEquals(500.0, treatmentPlan.getCostEstimate());
        assertEquals("Successful", treatmentPlan.getTreatmentOutcome());
        assertEquals("Notes about treatment", treatmentPlan.getTreatmentNotes());
        assertEquals("Vet Clinic", treatmentPlan.getClinicLocation());
        assertEquals(true, treatmentPlan.getIsInsured());
        assertEquals("Insurance Company A", treatmentPlan.getInsuranceDetails());
        assertEquals("Paid", treatmentPlan.getPaymentStatus());
        assertEquals(LocalDate.parse("2023-10-01"), treatmentPlan.getCreatedAt());
        assertEquals(LocalDate.parse("2023-11-01"), treatmentPlan.getUpdatedAt());
        assertEquals(pet, treatmentPlan.getPet());
    }

    @Test
    public void testSettersAndGetters() {
        TreatmentPlan newTreatmentPlan = new TreatmentPlan();
        newTreatmentPlan.setTreatmentPlanID(2L);
        newTreatmentPlan.setDiagnosis("Diagnosis B");
        newTreatmentPlan.setTreatmentType("Therapy");
        newTreatmentPlan.setTreatmentDescription("Description B");
        newTreatmentPlan.setPetCondition("Stable");
        newTreatmentPlan.setIsEmergency(false);
        newTreatmentPlan.setStartDate(LocalDate.parse("2023-09-10"));
        newTreatmentPlan.setEndDate(LocalDate.parse("2023-10-10"));
        newTreatmentPlan.setPrescribedMedications("Medication B");
        newTreatmentPlan.setTreatmentDuration("1 week");
        newTreatmentPlan.setNextSteps("No follow-up required");
        newTreatmentPlan.setVetName("Dr. Jane");
        newTreatmentPlan.setFollowUpDate(LocalDate.parse("2023-11-10"));
        newTreatmentPlan.setCostEstimate(300.0);
        newTreatmentPlan.setTreatmentOutcome("Pending");
        newTreatmentPlan.setTreatmentNotes("Additional notes");
        newTreatmentPlan.setClinicLocation("Health Clinic");
        newTreatmentPlan.setIsInsured(false);
        newTreatmentPlan.setInsuranceDetails("No insurance");
        newTreatmentPlan.setPaymentStatus("Unpaid");
        newTreatmentPlan.setCreatedAt(LocalDate.parse("2023-08-01"));
        newTreatmentPlan.setUpdatedAt(LocalDate.parse("2023-09-01"));
        newTreatmentPlan.setPet(pet);

        assertEquals(2L, newTreatmentPlan.getTreatmentPlanID());
        assertEquals("Diagnosis B", newTreatmentPlan.getDiagnosis());
        assertEquals("Therapy", newTreatmentPlan.getTreatmentType());
        assertEquals("Description B", newTreatmentPlan.getTreatmentDescription());
        assertEquals("Stable", newTreatmentPlan.getPetCondition());
        assertEquals(false, newTreatmentPlan.getIsEmergency());
        assertEquals(LocalDate.parse("2023-09-10"), newTreatmentPlan.getStartDate());
        assertEquals(LocalDate.parse("2023-10-10"), newTreatmentPlan.getEndDate());
        assertEquals("Medication B", newTreatmentPlan.getPrescribedMedications());
        assertEquals("1 week", newTreatmentPlan.getTreatmentDuration());
        assertEquals("No follow-up required", newTreatmentPlan.getNextSteps());
        assertEquals("Dr. Jane", newTreatmentPlan.getVetName());
        assertEquals(LocalDate.parse("2023-11-10"), newTreatmentPlan.getFollowUpDate());
        assertEquals(300.0, newTreatmentPlan.getCostEstimate());
        assertEquals("Pending", newTreatmentPlan.getTreatmentOutcome());
        assertEquals("Additional notes", newTreatmentPlan.getTreatmentNotes());
        assertEquals("Health Clinic", newTreatmentPlan.getClinicLocation());
        assertEquals(false, newTreatmentPlan.getIsInsured());
        assertEquals("No insurance", newTreatmentPlan.getInsuranceDetails());
        assertEquals("Unpaid", newTreatmentPlan.getPaymentStatus());
        assertEquals(LocalDate.parse("2023-08-01"), newTreatmentPlan.getCreatedAt());
        assertEquals(LocalDate.parse("2023-09-01"), newTreatmentPlan.getUpdatedAt());
        assertEquals(pet, newTreatmentPlan.getPet());
    }

    @Test
    public void testDefaultConstructor() {
        TreatmentPlan defaultTreatmentPlan = new TreatmentPlan();
        assertNotNull(defaultTreatmentPlan);
    }
}
