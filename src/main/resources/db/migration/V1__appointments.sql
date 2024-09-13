CREATE TABLE APPOINTMENT (
    id BIGINT NOT NULL AUTO_INCREMENT,
    veterinarianId BIGINT NOT NULL,
    clinicId INT NOT NULL,
    ownerName VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    appointmentTime TIME(6) NOT NULL,
    appointmentDate DATE NOT NULL,
    reason TEXT,
    petName VARCHAR(255) DEFAULT NULL,
    petType VARCHAR(255) DEFAULT NULL,
    petAge INT DEFAULT NULL,
    PRIMARY KEY (id)
);
