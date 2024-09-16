package au.edu.rmit.sept.webapp.models;

import java.util.Date;

public class MedicalHistory {

    private Long historyID;
    private Long petID;
    private Date lastVisitDate;
    private String lastDiagnosis;
    private String treatmentProvided;
    private String medicationsPrescribed;
    private String ongoingConditions;
    private Date nextScheduledVisit;

    // Default constructor
    public MedicalHistory() {}

    // Parameterized constructor
    public MedicalHistory(Long petID, Date lastVisitDate, String lastDiagnosis, String treatmentProvided, String medicationsPrescribed, String ongoingConditions, Date nextScheduledVisit) {
        this.petID = petID;
        this.lastVisitDate = lastVisitDate;
        this.lastDiagnosis = lastDiagnosis;
        this.treatmentProvided = treatmentProvided;
        this.medicationsPrescribed = medicationsPrescribed;
        this.ongoingConditions = ongoingConditions;
        this.nextScheduledVisit = nextScheduledVisit;
    }

    // Getters and Setters
    public Long getHistoryID() { return historyID; }
    public void setHistoryID(Long historyID) { this.historyID = historyID; }

    public Long getPetID() { return petID; }
    public void setPetID(Long petID) { this.petID = petID; }

    public Date getLastVisitDate() { return lastVisitDate; }
    public void setLastVisitDate(Date lastVisitDate) { this.lastVisitDate = lastVisitDate; }

    public String getLastDiagnosis() { return lastDiagnosis; }
    public void setLastDiagnosis(String lastDiagnosis) { this.lastDiagnosis = lastDiagnosis; }

    public String getTreatmentProvided() { return treatmentProvided; }
    public void setTreatmentProvided(String treatmentProvided) { this.treatmentProvided = treatmentProvided; }

    public String getMedicationsPrescribed() { return medicationsPrescribed; }
    public void setMedicationsPrescribed(String medicationsPrescribed) { this.medicationsPrescribed = medicationsPrescribed; }

    public String getOngoingConditions() { return ongoingConditions; }
    public void setOngoingConditions(String ongoingConditions) { this.ongoingConditions = ongoingConditions; }

    public Date getNextScheduledVisit() { return nextScheduledVisit; }
    public void setNextScheduledVisit(Date nextScheduledVisit) { this.nextScheduledVisit = nextScheduledVisit; }
}
