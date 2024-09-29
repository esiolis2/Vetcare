package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.Prescription;

import java.util.List;

public interface PrescriptionService {
    public List<Prescription> getAllPrescriptions();
    public List<Prescription> getPrescriptionsByPetId(Long petId);
    public void createPrescription(Prescription prescription);
}
