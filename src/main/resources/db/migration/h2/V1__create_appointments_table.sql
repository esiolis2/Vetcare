CREATE TABLE APPOINTMENT (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    veterinarianId BIGINT NOT NULL,
    clinicId INT NOT NULL,
    ownerId BIGINT NOT NULL,
    appointmentTime TIME NOT NULL,
    appointmentDate DATE NOT NULL,
    reason BIGINT,
    petId BIGINT NOT NULL
);
