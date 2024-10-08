package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.MedicalHistory;
import au.edu.rmit.sept.webapp.models.User;
import au.edu.rmit.sept.webapp.repositories.MedicalHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalHistoryServiceImpl implements MedicalHistoryService {

    private final MedicalHistoryRepository medicalHistoryRepository;

    @Autowired
    public MedicalHistoryServiceImpl(MedicalHistoryRepository medicalHistoryRepository) {
        this.medicalHistoryRepository = medicalHistoryRepository;
    }

    @Override
    public List<MedicalHistory> getMedicalHistoryByPetId(Long petId) {
        return medicalHistoryRepository.findMedicalHistoryByPetId(petId);
    }

    @Override
    public void createMedicalHistory(MedicalHistory medicalHistory) {
        medicalHistoryRepository.addMedicalHistory(medicalHistory);
    }

    @Override
    public List<MedicalHistory> getAllMedicalHistories() {
        return medicalHistoryRepository.findAllMedicalHistories();
    }
    @Override
    public void updateMedicalHistory(MedicalHistory medicalHistory, User loggedInUser) {
        if (medicalHistory == null || medicalHistory.getHistoryID() == null) {
            throw new IllegalArgumentException("Medical history or History ID cannot be null");
        }

        if (!"Vet".equals(loggedInUser.getUserType())) {
            medicalHistory.setPet(null);
        }

        System.out.println("Updating medical history with HistoryID: " + medicalHistory.getHistoryID());

        medicalHistoryRepository.updateMedicalHistory(medicalHistory);
    }


}
