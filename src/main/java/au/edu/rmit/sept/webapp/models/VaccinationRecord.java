package au.edu.rmit.sept.webapp.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class VaccinationRecord {

    private Long vaccinationID;
    private Long petID;
    private String vaccineName;
    private LocalDate administeredDate;
    private LocalDate nextDueDate;
    private String boosterRequired;
    private BigDecimal  dosage;
    private String veterinarianName;
    private String clinicName;
    private String status;
    private String additionalNotes;

    public VaccinationRecord(Long vaccinationID, Long petID, String vaccineName, LocalDate administeredDate, LocalDate nextDueDate, String boosterRequired, BigDecimal  dosage, String veterinarianName, String clinicName, String status, String additionalNotes) {
        this.vaccinationID = vaccinationID;
        this.petID = petID;
        this.vaccineName = vaccineName;
        this.administeredDate = administeredDate;
        this.nextDueDate = nextDueDate;
        this.boosterRequired = boosterRequired;
        this.dosage = dosage;
        this.veterinarianName = veterinarianName;
        this.clinicName = clinicName;
        this.status = status;
        this.additionalNotes = additionalNotes;
    }

    public VaccinationRecord() {
    }

    public Long getVaccinationID() {
        return vaccinationID;
    }

    public void setVaccinationID(Long vaccinationID) {
        this.vaccinationID = vaccinationID;
    }

    public Long getPetID() {
        return petID;
    }

    public void setPetID(Long petID) {
        this.petID = petID;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public LocalDate getAdministeredDate() {
        return administeredDate;
    }

    public void setAdministeredDate(LocalDate administeredDate) {
        this.administeredDate = administeredDate;
    }

    public LocalDate getNextDueDate() {
        return nextDueDate;
    }

    public void setNextDueDate(LocalDate nextDueDate) {
        this.nextDueDate = nextDueDate;
    }

    public String getBoosterRequired() {
        return boosterRequired;
    }

    public void setBoosterRequired(String boosterRequired) {
        this.boosterRequired = boosterRequired;
    }

    public BigDecimal getDosage() {
        return dosage;
    }

    public void setDosage(BigDecimal dosage) {
        this.dosage = dosage;
    }

    public String getVeterinarianName() {
        return veterinarianName;
    }

    public void setVeterinarianName(String veterinarianName) {
        this.veterinarianName = veterinarianName;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }
}
