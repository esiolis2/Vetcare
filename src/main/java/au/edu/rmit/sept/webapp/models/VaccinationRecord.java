package au.edu.rmit.sept.webapp.models;

import java.time.LocalDate;


public class VaccinationRecord {

    private Long vaccinationID;

    private PetInformation pet;

    private String vaccineName;

    private LocalDate administeredDate;

    private LocalDate nextDueDate;

    private String boosterRequired;


    public VaccinationRecord() {}

    public VaccinationRecord(PetInformation pet, String vaccineName, LocalDate administeredDate, LocalDate nextDueDate, String boosterRequired) {
        this.pet = pet;
        this.vaccineName = vaccineName;
        this.administeredDate = administeredDate;
        this.nextDueDate = nextDueDate;
        this.boosterRequired = boosterRequired;
    }

    public Long getVaccinationID() {
        return vaccinationID;
    }

    public void setVaccinationID(Long vaccinationID) {
        this.vaccinationID = vaccinationID;
    }

    public PetInformation getPet() {
        return pet;
    }

    public void setPet(PetInformation pet) {
        this.pet = pet;
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
}
