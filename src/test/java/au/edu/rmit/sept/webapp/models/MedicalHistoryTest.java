package au.edu.rmit.sept.webapp.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MedicalHistoryTest {

    private MedicalHistory medicalHistory;
    private PetInformation pet;

    @BeforeEach
    public void setUp() {
        pet = new PetInformation(1L, "Buddy", 3, "Male", 25.5, "Golden Retriever", null, 1L);
        medicalHistory = new MedicalHistory(
                1L,
                1L,
                LocalDate.parse("2023-10-15"),
                "No issues",
                "None",
                "No medications",
                "None",
                LocalDate.parse("2024-10-15"),
                pet
        );
    }


    @Test
    public void testSettersAndGetters() {
        MedicalHistory newMedicalHistory = new MedicalHistory();
        newMedicalHistory.setHistoryID(2L);
        newMedicalHistory.setPetID(2L);
        newMedicalHistory.setLastVisitDate(LocalDate.parse("2022-10-15"));
        newMedicalHistory.setLastDiagnosis("Allergy");
        newMedicalHistory.setTreatmentProvided("Antihistamines");
        newMedicalHistory.setMedicationsPrescribed("Cetirizine");
        newMedicalHistory.setOngoingConditions("Allergy Symptoms");
        newMedicalHistory.setNextScheduledVisit(LocalDate.parse("2023-10-15"));
        newMedicalHistory.setPet(pet);

        assertEquals(2L, newMedicalHistory.getHistoryID());
        assertEquals(2L, newMedicalHistory.getPetID());
        assertEquals(LocalDate.parse("2022-10-15"), newMedicalHistory.getLastVisitDate());
        assertEquals("Allergy", newMedicalHistory.getLastDiagnosis());
        assertEquals("Antihistamines", newMedicalHistory.getTreatmentProvided());
        assertEquals("Cetirizine", newMedicalHistory.getMedicationsPrescribed());
        assertEquals("Allergy Symptoms", newMedicalHistory.getOngoingConditions());
        assertEquals(LocalDate.parse("2023-10-15"), newMedicalHistory.getNextScheduledVisit());
    }

    @Test
    public void testDefaultConstructor() {
        MedicalHistory defaultMedicalHistory = new MedicalHistory();
        assertNotNull(defaultMedicalHistory);
    }
}
