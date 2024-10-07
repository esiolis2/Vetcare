package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.MedicalHistory;
import au.edu.rmit.sept.webapp.models.User;

import java.util.List;

public interface MedicalHistoryService {
    List<MedicalHistory> getMedicalHistoryByPetId(Long petId);
    void createMedicalHistory(MedicalHistory medicalHistory);
   List<MedicalHistory> getAllMedicalHistories();
    void updateMedicalHistory(MedicalHistory medicalHistory, User loggedInUser);
}
