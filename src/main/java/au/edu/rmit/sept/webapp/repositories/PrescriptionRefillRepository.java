package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.PrescriptionRefillRequest;

import java.util.List;

public interface PrescriptionRefillRepository {
    void saveRefillRequest(PrescriptionRefillRequest refillRequest);
    public List<PrescriptionRefillRequest> findAllRefillRequests();
}
