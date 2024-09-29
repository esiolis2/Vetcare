package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.PrescriptionRefillRequest;

public interface PrescriptionRefillService {
    void saveRefillRequest(PrescriptionRefillRequest refillRequest);
}
