package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.PrescriptionRefillRequest;

import java.util.List;

public interface PrescriptionRefillService {
    void saveRefillRequest(PrescriptionRefillRequest refillRequest);
    public List<PrescriptionRefillRequest> getAllRefillRequests();
}
