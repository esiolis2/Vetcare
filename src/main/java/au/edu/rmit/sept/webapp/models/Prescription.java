package au.edu.rmit.sept.webapp.models;

import java.time.LocalDate;

public class Prescription {

    private Long prescriptionID;
    private String medicationName;
    private String dosage;
    private String instructions;
    private LocalDate nextRefillDate;
    private int quantityPrescribed;
    private int refillCount;
    private LocalDate expiryDate;
    private PetInformation pet;
    private LocalDate updatedAt;

    public Prescription(Long prescriptionID, String medicationName, String dosage, String instructions, LocalDate nextRefillDate,
                        int quantityPrescribed, int refillCount, LocalDate expiryDate, PetInformation pet,
                        LocalDate updatedAt) {
        this.prescriptionID = prescriptionID;
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.instructions = instructions;
        this.nextRefillDate = nextRefillDate;
        this.quantityPrescribed = quantityPrescribed;
        this.refillCount = refillCount;
        this.expiryDate = expiryDate;
        this.pet = pet;
        this.updatedAt = updatedAt;
    }



    public Long getPrescriptionID() {
        return prescriptionID;
    }

    public void setPrescriptionID(Long prescriptionID) {
        this.prescriptionID = prescriptionID;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public LocalDate getNextRefillDate() {
        return nextRefillDate;
    }

    public void setNextRefillDate(LocalDate nextRefillDate) {
        this.nextRefillDate = nextRefillDate;
    }

    public int getQuantityPrescribed() {
        return quantityPrescribed;
    }

    public void setQuantityPrescribed(int quantityPrescribed) {
        this.quantityPrescribed = quantityPrescribed;
    }

    public int getRefillCount() {
        return refillCount;
    }

    public void setRefillCount(int refillCount) {
        this.refillCount = refillCount;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public PetInformation getPet() {
        return pet;
    }

    public void setPet(PetInformation pet) {
        this.pet = pet;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }
}
