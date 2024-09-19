package au.edu.rmit.sept.webapp.models;

import java.time.LocalDate;

public class TreatmentPlan {
    private PetInformation pet;

    private Long treatmentPlanID;
//    private Long petID;
    private String diagnosis;
    private String treatmentType;
    private String treatmentDescription;
    private String petCondition;
    private Boolean isEmergency;
    private LocalDate startDate;
    private LocalDate endDate;
    private String prescribedMedications;
    private String treatmentDuration;
    private String nextSteps;
    private String vetName;
    private LocalDate followUpDate;
    private Double costEstimate;
    private String treatmentOutcome;
    private String treatmentNotes;
    private String clinicLocation;
    private Boolean isInsured;
    private String insuranceDetails;
    private String paymentStatus;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public TreatmentPlan(Long treatmentPlanID, String diagnosis, String treatmentType,
                         String treatmentDescription, String petCondition, Boolean isEmergency, LocalDate startDate,
                         LocalDate endDate, String prescribedMedications, String treatmentDuration, String nextSteps,
                         String vetName, LocalDate followUpDate, Double costEstimate, String treatmentOutcome,
                         String treatmentNotes, String clinicLocation, Boolean isInsured, String insuranceDetails,
                         String paymentStatus, LocalDate createdAt, LocalDate updatedAt) {
        this.treatmentPlanID = treatmentPlanID;
//        this.petID = petID;
        this.diagnosis = diagnosis;
        this.treatmentType = treatmentType;
        this.treatmentDescription = treatmentDescription;
        this.petCondition = petCondition;
        this.isEmergency = isEmergency;
        this.startDate = startDate;
        this.endDate = endDate;
        this.prescribedMedications = prescribedMedications;
        this.treatmentDuration = treatmentDuration;
        this.nextSteps = nextSteps;
        this.vetName = vetName;
        this.followUpDate = followUpDate;
        this.costEstimate = costEstimate;
        this.treatmentOutcome = treatmentOutcome;
        this.treatmentNotes = treatmentNotes;
        this.clinicLocation = clinicLocation;
        this.isInsured = isInsured;
        this.insuranceDetails = insuranceDetails;
        this.paymentStatus = paymentStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public PetInformation getPet() {
        return pet;
    }

    public void setPet(PetInformation pet) {
        this.pet = pet;
    }
    public Long getTreatmentPlanID() {
        return treatmentPlanID;
    }

    public void setTreatmentPlanID(Long treatmentPlanID) {
        this.treatmentPlanID = treatmentPlanID;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatmentType() {
        return treatmentType;
    }

    public void setTreatmentType(String treatmentType) {
        this.treatmentType = treatmentType;
    }

    public String getTreatmentDescription() {
        return treatmentDescription;
    }

    public void setTreatmentDescription(String treatmentDescription) {
        this.treatmentDescription = treatmentDescription;
    }

    public String getPetCondition() {
        return petCondition;
    }

    public void setPetCondition(String petCondition) {
        this.petCondition = petCondition;
    }

    public Boolean getIsEmergency() {
        return isEmergency;
    }

    public void setIsEmergency(Boolean isEmergency) {
        this.isEmergency = isEmergency;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
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

    public String getVetName() {
        return vetName;
    }

    public void setVetName(String vetName) {
        this.vetName = vetName;
    }

    public LocalDate getFollowUpDate() {
        return followUpDate;
    }

    public void setFollowUpDate(LocalDate followUpDate) {
        this.followUpDate = followUpDate;
    }

    public Double getCostEstimate() {
        return costEstimate;
    }

    public void setCostEstimate(Double costEstimate) {
        this.costEstimate = costEstimate;
    }

    public String getTreatmentOutcome() {
        return treatmentOutcome;
    }

    public void setTreatmentOutcome(String treatmentOutcome) {
        this.treatmentOutcome = treatmentOutcome;
    }

    public String getTreatmentNotes() {
        return treatmentNotes;
    }

    public void setTreatmentNotes(String treatmentNotes) {
        this.treatmentNotes = treatmentNotes;
    }

    public String getClinicLocation() {
        return clinicLocation;
    }

    public void setClinicLocation(String clinicLocation) {
        this.clinicLocation = clinicLocation;
    }

    public Boolean getIsInsured() {
        return isInsured;
    }

    public void setIsInsured(Boolean isInsured) {
        this.isInsured = isInsured;
    }

    public String getInsuranceDetails() {
        return insuranceDetails;
    }

    public void setInsuranceDetails(String insuranceDetails) {
        this.insuranceDetails = insuranceDetails;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }
}
