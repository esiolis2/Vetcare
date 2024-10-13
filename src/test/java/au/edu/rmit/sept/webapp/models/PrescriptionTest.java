package au.edu.rmit.sept.webapp.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PrescriptionTest {

    private Prescription prescription;

    @BeforeEach
    public void setUp() {
        prescription = new Prescription();
    }

    @Test
    public void testNoArgsConstructor() {
        assertNotNull(prescription);
    }

    @Test
    public void testAllArgsConstructor() {
        Long prescriptionID = 1L;
        String medicationName = "Antibiotic";
        String dosage = "500mg";
        String instructions = "Take with food";
        LocalDate nextRefillDate = LocalDate.of(2023, 11, 15);
        int quantityPrescribed = 30;
        int refillCount = 2;
        LocalDate expiryDate = LocalDate.of(2024, 11, 15);
        PetInformation pet = new PetInformation(100L, "Buddy", 5, "Male", 12.5, "Golden Retriever", LocalDate.of(2018, 5, 12), 1L);
        LocalDate updatedAt = LocalDate.of(2023, 10, 10);

        Prescription prescription = new Prescription(prescriptionID, medicationName, dosage, instructions, nextRefillDate,
                quantityPrescribed, refillCount, expiryDate, pet, updatedAt);

        assertEquals(prescriptionID, prescription.getPrescriptionID());
        assertEquals(medicationName, prescription.getMedicationName());
        assertEquals(dosage, prescription.getDosage());
        assertEquals(instructions, prescription.getInstructions());
        assertEquals(nextRefillDate, prescription.getNextRefillDate());
        assertEquals(quantityPrescribed, prescription.getQuantityPrescribed());
        assertEquals(refillCount, prescription.getRefillCount());
        assertEquals(expiryDate, prescription.getExpiryDate());
        assertEquals(pet, prescription.getPet());
        assertEquals(updatedAt, prescription.getUpdatedAt());
    }

    @Test
    public void testSettersAndGetters() {
        // Arrange
        Long prescriptionID = 2L;
        String medicationName = "Painkiller";
        String dosage = "250mg";
        String instructions = "Take every 6 hours";
        LocalDate nextRefillDate = LocalDate.of(2023, 12, 1);
        int quantityPrescribed = 15;
        int refillCount = 1;
        LocalDate expiryDate = LocalDate.of(2024, 12, 1);
        PetInformation pet = new PetInformation(101L, "Max", 3, "Male", 20.0, "Labrador", LocalDate.of(2019, 7, 22), 2L);
        LocalDate updatedAt = LocalDate.now();

        prescription.setPrescriptionID(prescriptionID);
        prescription.setMedicationName(medicationName);
        prescription.setDosage(dosage);
        prescription.setInstructions(instructions);
        prescription.setNextRefillDate(nextRefillDate);
        prescription.setQuantityPrescribed(quantityPrescribed);
        prescription.setRefillCount(refillCount);
        prescription.setExpiryDate(expiryDate);
        prescription.setPet(pet);
        prescription.setUpdatedAt(updatedAt);

        assertEquals(prescriptionID, prescription.getPrescriptionID());
        assertEquals(medicationName, prescription.getMedicationName());
        assertEquals(dosage, prescription.getDosage());
        assertEquals(instructions, prescription.getInstructions());
        assertEquals(nextRefillDate, prescription.getNextRefillDate());
        assertEquals(quantityPrescribed, prescription.getQuantityPrescribed());
        assertEquals(refillCount, prescription.getRefillCount());
        assertEquals(expiryDate, prescription.getExpiryDate());
        assertEquals(pet, prescription.getPet());
        assertEquals(updatedAt, prescription.getUpdatedAt());
    }

    @Test
    public void testSetUpdatedAtWithNullValue() {
        prescription.setUpdatedAt(null);

        assertEquals(LocalDate.now(), prescription.getUpdatedAt());
    }

    @Test
    public void testEmptyFields() {
        assertNull(prescription.getPrescriptionID());
        assertNull(prescription.getMedicationName());
        assertNull(prescription.getDosage());
        assertNull(prescription.getInstructions());
        assertNull(prescription.getNextRefillDate());
        assertEquals(0, prescription.getQuantityPrescribed());
        assertEquals(0, prescription.getRefillCount());
        assertNull(prescription.getExpiryDate());
        assertNull(prescription.getPet());
        assertNull(prescription.getUpdatedAt());
    }
}
