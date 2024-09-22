package au.edu.rmit.sept.webapp.models;

import java.time.LocalDate;

public class TreatmentPlan {

    private Long treatmentPlanID;
    private Long petID;
    private String treatmentDescription;
    private LocalDate startDate;
    private String prescribedMedications;
    private String treatmentDuration;
    private String nextSteps;

    public TreatmentPlan() {
    }

    public TreatmentPlan(Long treatmentPlanID, Long petID, String treatmentDescription, LocalDate startDate,
                         String prescribedMedications, String treatmentDuration, String nextSteps) {
        this.treatmentPlanID = treatmentPlanID;
        this.petID = petID;
        this.treatmentDescription = treatmentDescription;
        this.startDate = startDate;
        this.prescribedMedications = prescribedMedications;
        this.treatmentDuration = treatmentDuration;
        this.nextSteps = nextSteps;
    }


    public Long getTreatmentPlanID() {
        return treatmentPlanID;
    }

    public void setTreatmentPlanID(Long treatmentPlanID) {
        this.treatmentPlanID = treatmentPlanID;
    }

    public Long getPetID() {
        return petID;
    }

    public void setPetID(Long petID) {
        this.petID = petID;
    }

    public String getTreatmentDescription() {
        return treatmentDescription;
    }

    public void setTreatmentDescription(String treatmentDescription) {
        this.treatmentDescription = treatmentDescription;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getPrescribedMedications() {
        return prescribedMedications;
    }

    public void setPrescribedMedications(String prescribedMedications) {
        this.prescribedMedications = prescribedMedications;
    }

    public String getTreatmentDuration() {
        return treatmentDuration;
    }

    public void setTreatmentDuration(String treatmentDuration) {
        this.treatmentDuration = treatmentDuration;
    }

    public String getNextSteps() {
        return nextSteps;
    }

    public void setNextSteps(String nextSteps) {
        this.nextSteps = nextSteps;
    }
}
