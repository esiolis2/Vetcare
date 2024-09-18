CREATE TABLE IF NOT EXISTS pet_info (
    PetID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Age INT,
    Gender VARCHAR(10),
    Weight DECIMAL(5, 2),
    Breed VARCHAR(100),
    BirthDate DATE,
    OwnerName VARCHAR(100),
    OwnerContact VARCHAR(15)
);