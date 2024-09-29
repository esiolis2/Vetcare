package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.Prescription;

import java.util.List;

public interface PrescriptionRepository {

    public List<Prescription> findAll();
    public List<Prescription> findByPetId(Long petId);
    public void addPrescription(Prescription prescription);
}
