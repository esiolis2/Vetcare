package au.edu.rmit.sept.webapp.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class VaccinationRecordTest {

    private VaccinationRecord vaccinationRecord;

    @BeforeEach
    public void setUp() {
        vaccinationRecord = new VaccinationRecord();
    }

    @Test
    public void testNoArgsConstructor() {
        assertNotNull(vaccinationRecord);
    }

    @Test
    public void testAllArgsConstructor() {
        Long vaccinationID = 1L;
        Long petID = 100L;
        String vaccineName = "Rabies";
        LocalDate administeredDate = LocalDate.of(2023, 9, 1);
        LocalDate nextDueDate = LocalDate.of(2024, 9, 1);
        String boosterRequired = "Yes";
        BigDecimal dosage = new BigDecimal("0.5");
        String veterinarianName = "Dr. Mark Smith";
        String clinicName = "City Vet Clinic";
        String status = "Completed";
        String additionalNotes = "Next appointment required.";

        VaccinationRecord vaccinationRecord = new VaccinationRecord(vaccinationID, petID, vaccineName, administeredDate, nextDueDate, boosterRequired, dosage, veterinarianName, clinicName, status, additionalNotes);

        assertEquals(vaccinationID, vaccinationRecord.getVaccinationID());
        assertEquals(petID, vaccinationRecord.getPetID());
        assertEquals(vaccineName, vaccinationRecord.getVaccineName());
        assertEquals(administeredDate, vaccinationRecord.getAdministeredDate());
        assertEquals(nextDueDate, vaccinationRecord.getNextDueDate());
        assertEquals(boosterRequired, vaccinationRecord.getBoosterRequired());
        assertEquals(dosage, vaccinationRecord.getDosage());
        assertEquals(veterinarianName, vaccinationRecord.getVeterinarianName());
        assertEquals(clinicName, vaccinationRecord.getClinicName());
        assertEquals(status, vaccinationRecord.getStatus());
        assertEquals(additionalNotes, vaccinationRecord.getAdditionalNotes());
    }

    @Test
    public void testSettersAndGetters() {
        Long vaccinationID = 2L;
        Long petID = 101L;
        String vaccineName = "Distemper";
        LocalDate administeredDate = LocalDate.of(2022, 8, 15);
        LocalDate nextDueDate = LocalDate.of(2023, 8, 15);
        String boosterRequired = "No";
        BigDecimal dosage = new BigDecimal("1.0");
        String veterinarianName = "Dr. Alice Johnson";
        String clinicName = "Suburb Vet Clinic";
        String status = "Pending";
        String additionalNotes = "Monitor side effects.";

        vaccinationRecord.setVaccinationID(vaccinationID);
        vaccinationRecord.setPetID(petID);
        vaccinationRecord.setVaccineName(vaccineName);
        vaccinationRecord.setAdministeredDate(administeredDate);
        vaccinationRecord.setNextDueDate(nextDueDate);
        vaccinationRecord.setBoosterRequired(boosterRequired);
        vaccinationRecord.setDosage(dosage);
        vaccinationRecord.setVeterinarianName(veterinarianName);
        vaccinationRecord.setClinicName(clinicName);
        vaccinationRecord.setStatus(status);
        vaccinationRecord.setAdditionalNotes(additionalNotes);

        assertEquals(vaccinationID, vaccinationRecord.getVaccinationID());
        assertEquals(petID, vaccinationRecord.getPetID());
        assertEquals(vaccineName, vaccinationRecord.getVaccineName());
        assertEquals(administeredDate, vaccinationRecord.getAdministeredDate());
        assertEquals(nextDueDate, vaccinationRecord.getNextDueDate());
        assertEquals(boosterRequired, vaccinationRecord.getBoosterRequired());
        assertEquals(dosage, vaccinationRecord.getDosage());
        assertEquals(veterinarianName, vaccinationRecord.getVeterinarianName());
        assertEquals(clinicName, vaccinationRecord.getClinicName());
        assertEquals(status, vaccinationRecord.getStatus());
        assertEquals(additionalNotes, vaccinationRecord.getAdditionalNotes());
    }

    @Test
    public void testSetPet() {
        PetInformation pet = new PetInformation(100L, "Buddy", 5, "Male", 12.5, "Golden Retriever", LocalDate.of(2018, 5, 12), 1L);

        vaccinationRecord.setPet(pet);

        assertEquals(pet.getPetID(), vaccinationRecord.getPetID());
    }

    @Test
    public void testEmptyFields() {
        assertNull(vaccinationRecord.getVaccinationID());
        assertNull(vaccinationRecord.getPetID());
        assertNull(vaccinationRecord.getVaccineName());
        assertNull(vaccinationRecord.getAdministeredDate());
        assertNull(vaccinationRecord.getNextDueDate());
        assertNull(vaccinationRecord.getBoosterRequired());
        assertNull(vaccinationRecord.getDosage());
        assertNull(vaccinationRecord.getVeterinarianName());
        assertNull(vaccinationRecord.getClinicName());
        assertNull(vaccinationRecord.getStatus());
        assertNull(vaccinationRecord.getAdditionalNotes());
    }
}
