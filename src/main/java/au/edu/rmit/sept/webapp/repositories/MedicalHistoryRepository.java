package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.MedicalHistory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalHistoryRepository {

    List<MedicalHistory> findMedicalHistoryByPetId(Long petId);
    void addMedicalHistory(MedicalHistory medicalHistory);
    List<MedicalHistory> findAllMedicalHistories();
    public void updateMedicalHistory(MedicalHistory medicalHistory);
}
