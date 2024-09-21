CREATE TABLE IF NOT EXISTS VaccinationRecords (
    VaccinationID INT AUTO_INCREMENT PRIMARY KEY,
    PetID INT,
    VaccineName VARCHAR(100) NOT NULL,
    AdministeredDate DATE NOT NULL,
    NextDueDate DATE,
    BoosterRequired VARCHAR(255),
    Dosage DECIMAL(4, 2),
    VeterinarianName VARCHAR(100),
    ClinicName VARCHAR(100),
    Status VARCHAR(50),
    AdditionalNotes TEXT,
    FOREIGN KEY (PetID) REFERENCES pet_info(PetID) ON DELETE CASCADE
);