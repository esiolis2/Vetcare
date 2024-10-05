CREATE TABLE APPOINTMENT (
    id BIGINT NOT NULL AUTO_INCREMENT,
    veterinarianId BIGINT NOT NULL,
    clinicId INT NOT NULL,
    ownerId BIGINT NOT NULL,
    appointmentTime TIME(6) NOT NULL,
    appointmentDate DATE NOT NULL,
    reason BIGINT,
    petId BIGINT NOT NULL,
    PRIMARY KEY (id)
);
