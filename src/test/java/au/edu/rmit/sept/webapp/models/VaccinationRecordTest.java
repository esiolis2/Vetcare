package au.edu.rmit.sept.webapp.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class VaccinationRecordTest {

    private VaccinationRecord vaccinationRecord;
    private PetInformation pet;

    @BeforeEach
    public void setUp() {
        pet = new PetInformation(1L, "Buddy", 3, "Male", 25.5, "Golden Retriever", null, 1L);
        vaccinationRecord = new VaccinationRecord(
                1L,
                pet.getPetID(),
                "Rabies",
                LocalDate.parse("2023-10-15"),
                LocalDate.parse("2024-10-15"),
                "Yes",
                new BigDecimal("0.5"),
                "Dr. Smith",
                "Happy Paws Clinic",
                "Completed",
                "No side effects observed"
        );
    }

    @Test
    public void testParameterizedConstructor() {
        assertNotNull(vaccinationRecord);
        assertEquals(1L, vaccinationRecord.getVaccinationID());
        assertEquals(1L, vaccinationRecord.getPetID());
        assertEquals("Rabies", vaccinationRecord.getVaccineName());
        assertEquals(LocalDate.parse("2023-10-15"), vaccinationRecord.getAdministeredDate());
        assertEquals(LocalDate.parse("2024-10-15"), vaccinationRecord.getNextDueDate());
        assertEquals("Yes", vaccinationRecord.getBoosterRequired());
        assertEquals(new BigDecimal("0.5"), vaccinationRecord.getDosage());
        assertEquals("Dr. Smith", vaccinationRecord.getVeterinarianName());
        assertEquals("Happy Paws Clinic", vaccinationRecord.getClinicName());
        assertEquals("Completed", vaccinationRecord.getStatus());
        assertEquals("No side effects observed", vaccinationRecord.getAdditionalNotes());
    }

    @Test
    public void testSettersAndGetters() {
        VaccinationRecord newVaccinationRecord = new VaccinationRecord();
        newVaccinationRecord.setVaccinationID(2L);
        newVaccinationRecord.setPetID(2L);
        newVaccinationRecord.setVaccineName("Distemper");
        newVaccinationRecord.setAdministeredDate(LocalDate.parse("2022-08-15"));
        newVaccinationRecord.setNextDueDate(LocalDate.parse("2023-08-15"));
        newVaccinationRecord.setBoosterRequired("No");
        newVaccinationRecord.setDosage(new BigDecimal("1.0"));
        newVaccinationRecord.setVeterinarianName("Dr. Jane");
        newVaccinationRecord.setClinicName("PetCare Clinic");
        newVaccinationRecord.setStatus("Pending");
        newVaccinationRecord.setAdditionalNotes("Monitor for side effects");

        assertEquals(2L, newVaccinationRecord.getVaccinationID());
        assertEquals(2L, newVaccinationRecord.getPetID());
        assertEquals("Distemper", newVaccinationRecord.getVaccineName());
        assertEquals(LocalDate.parse("2022-08-15"), newVaccinationRecord.getAdministeredDate());
        assertEquals(LocalDate.parse("2023-08-15"), newVaccinationRecord.getNextDueDate());
        assertEquals("No", newVaccinationRecord.getBoosterRequired());
        assertEquals(new BigDecimal("1.0"), newVaccinationRecord.getDosage());
        assertEquals("Dr. Jane", newVaccinationRecord.getVeterinarianName());
        assertEquals("PetCare Clinic", newVaccinationRecord.getClinicName());
        assertEquals("Pending", newVaccinationRecord.getStatus());
        assertEquals("Monitor for side effects", newVaccinationRecord.getAdditionalNotes());
    }

    @Test
    public void testDefaultConstructor() {
        VaccinationRecord defaultVaccinationRecord = new VaccinationRecord();
        assertNotNull(defaultVaccinationRecord);
    }

    @Test
    public void testSetPet() {
        VaccinationRecord vaccinationRecordWithPet = new VaccinationRecord();
        vaccinationRecordWithPet.setPet(pet);

        assertEquals(1L, vaccinationRecordWithPet.getPetID());
    }
}
