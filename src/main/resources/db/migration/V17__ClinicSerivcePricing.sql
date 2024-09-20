CREATE TABLE CLINIC_SERVICE_PRICING (
    clinic_id BIGINT NOT NULL,
    service_id BIGINT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (clinic_id, service_id),
    FOREIGN KEY (clinic_id) REFERENCES CLINIC(id),
    FOREIGN KEY (service_id) REFERENCES CLINIC_SERVICE(id)
);
