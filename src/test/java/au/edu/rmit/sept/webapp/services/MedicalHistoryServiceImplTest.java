package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.MedicalHistory;
import au.edu.rmit.sept.webapp.repositories.MedicalHistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MedicalHistoryServiceImplTest {

    private MedicalHistoryService medicalHistoryService;
    private MedicalHistoryRepository medicalHistoryRepository;

    @BeforeEach
    public void setUp() {
        medicalHistoryRepository = Mockito.mock(MedicalHistoryRepository.class);
        medicalHistoryService = new MedicalHistoryServiceImpl(medicalHistoryRepository);
    }

    @Test
    public void getMedicalHistoryByPetId_shouldReturnRecords() {
        MedicalHistory record = new MedicalHistory(1L, null, "Diagnosis", "Treatment", "Medication", "Condition", null);
        when(medicalHistoryRepository.findMedicalHistoryByPetId(1L)).thenReturn(List.of(record));

        List<MedicalHistory> records = medicalHistoryService.getMedicalHistoryByPetId(1L);
        assertEquals(1, records.size());
        assertEquals("Diagnosis", records.get(0).getLastDiagnosis());
        verify(medicalHistoryRepository, times(1)).findMedicalHistoryByPetId(1L);
    }

    @Test
    public void createMedicalHistory_shouldCallRepository() {
        MedicalHistory record = new MedicalHistory(1L, null, "Diagnosis", "Treatment", "Medication", "Condition", null);

        medicalHistoryService.createMedicalHistory(record);
        verify(medicalHistoryRepository, times(1)).addMedicalHistory(record);
    }
}
