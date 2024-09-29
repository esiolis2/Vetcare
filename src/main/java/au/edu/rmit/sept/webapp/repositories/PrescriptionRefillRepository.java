package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.PrescriptionRefillRequest;

public interface PrescriptionRefillRepository {
    void saveRefillRequest(PrescriptionRefillRequest refillRequest);
}
