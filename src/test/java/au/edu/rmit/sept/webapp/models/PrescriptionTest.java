package au.edu.rmit.sept.webapp.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrescriptionTest {

    @Test
    public void testPrescriptionConstructorAndGetters() {
        PetInformation pet = new PetInformation(1L, "Buddy", 3, "Male", 25.5, "Golden Retriever", null, 1L);
        LocalDate nextRefillDate = LocalDate.of(2024, 10, 15);
        LocalDate expiryDate = LocalDate.of(2025, 10, 15);
        LocalDate updatedAt = LocalDate.now();

        Prescription prescription = new Prescription(
                1L,
                "Fidozol 50mg",
                "1 tablet twice a day",
                "Take with food",
                nextRefillDate,
                30,
                2,
                expiryDate,
                pet,
                updatedAt
        );

        assertEquals(1L, prescription.getPrescriptionID());
        assertEquals("Fidozol 50mg", prescription.getMedicationName());
        assertEquals("1 tablet twice a day", prescription.getDosage());
        assertEquals("Take with food", prescription.getInstructions());
        assertEquals(nextRefillDate, prescription.getNextRefillDate());
        assertEquals(30, prescription.getQuantityPrescribed());
        assertEquals(2, prescription.getRefillCount());
        assertEquals(expiryDate, prescription.getExpiryDate());
        assertEquals(pet, prescription.getPet());
        assertEquals(updatedAt, prescription.getUpdatedAt());
    }

    @Test
    public void testSettersForPrescription() {
        Prescription prescription = new Prescription();
        PetInformation pet = new PetInformation(2L, "Max", 4, "Female", 20.0, "Labrador", null, 2L);
        LocalDate today = LocalDate.now();

        prescription.setPrescriptionID(2L);
        prescription.setMedicationName("AllerPet 10mg");
        prescription.setDosage("1 tablet daily");
        prescription.setInstructions("Administer with food");
        prescription.setNextRefillDate(today.plusMonths(3));
        prescription.setQuantityPrescribed(60);
        prescription.setRefillCount(5);
        prescription.setExpiryDate(today.plusYears(1));
        prescription.setPet(pet);
        prescription.setUpdatedAt(today);

        assertEquals(2L, prescription.getPrescriptionID());
        assertEquals("AllerPet 10mg", prescription.getMedicationName());
        assertEquals("1 tablet daily", prescription.getDosage());
        assertEquals("Administer with food", prescription.getInstructions());
        assertEquals(today.plusMonths(3), prescription.getNextRefillDate());
        assertEquals(60, prescription.getQuantityPrescribed());
        assertEquals(5, prescription.getRefillCount());
        assertEquals(today.plusYears(1), prescription.getExpiryDate());
        assertEquals(pet, prescription.getPet());
        assertEquals(today, prescription.getUpdatedAt());
    }
}
