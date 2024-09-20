CREATE TABLE IF NOT EXISTS TreatmentPlans (
    TreatmentPlanID INT AUTO_INCREMENT PRIMARY KEY,
    PetID INT,
    TreatmentDescription VARCHAR(255) NOT NULL,
    StartDate DATE NOT NULL,
    PrescribedMedications VARCHAR(255),
    TreatmentDuration VARCHAR(100),
    NextSteps VARCHAR(255),
    FOREIGN KEY (PetID) REFERENCES PET_INFO(PetID) ON DELETE CASCADE
);