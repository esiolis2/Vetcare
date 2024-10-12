package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.MedicalHistory;

import java.util.List;

public interface MedicalHistoryService {
    List<MedicalHistory> getMedicalHistoryByPetId(Long petId);
    void createMedicalHistory(MedicalHistory medicalHistory);
}
