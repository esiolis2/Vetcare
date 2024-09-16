CREATE TABLE IF NOT EXISTS MedicalHistory (
    HistoryID INT AUTO_INCREMENT PRIMARY KEY,
    PetID INT,
    LastVisitDate DATE NOT NULL,
    LastDiagnosis VARCHAR(255),
    TreatmentProvided VARCHAR(255),
    MedicationsPrescribed VARCHAR(255),
    OngoingConditions VARCHAR(255),
    NextScheduledVisit DATE,
    FOREIGN KEY (PetID) REFERENCES PET_INFO(PetID) ON DELETE CASCADE
);
