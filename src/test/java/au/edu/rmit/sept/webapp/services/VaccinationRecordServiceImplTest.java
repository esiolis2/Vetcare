package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.VaccinationRecord;
import au.edu.rmit.sept.webapp.repositories.VaccinationRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class VaccinationRecordServiceImplTest {

    private VaccinationRecordService vaccinationRecordService;
    private VaccinationRecordRepository vaccinationRecordRepository;

    @BeforeEach
    public void setUp() {
        vaccinationRecordRepository = Mockito.mock(VaccinationRecordRepository.class);
        vaccinationRecordService = new VaccinationRecordServiceImpl(vaccinationRecordRepository);
    }

    @Test
    public void getVaccinationRecordByPetId_shouldReturnRecords() {
        VaccinationRecord record = new VaccinationRecord(1L, 1L, "Rabies", LocalDate.now(),
                LocalDate.now().plusYears(1), "Yes", new BigDecimal("2.5"), "Dr. Smith",
                "Clinic A", "Completed", "All good");
        when(vaccinationRecordRepository.findVaccinationRecordByPetId(1L)).thenReturn(List.of(record));

        List<VaccinationRecord> records = vaccinationRecordService.getVaccinationRecordByPetId(1L);
        assertEquals(1, records.size());
        assertEquals("Rabies", records.get(0).getVaccineName());
        verify(vaccinationRecordRepository, times(1)).findVaccinationRecordByPetId(1L);
    }

    @Test
    public void createVaccinationRecord_shouldCallRepository() {
        VaccinationRecord record = new VaccinationRecord(1L, 1L, "Rabies", LocalDate.now(),
                LocalDate.now().plusYears(1), "Yes", new BigDecimal("2.5"), "Dr. Smith",
                "Clinic A", "Completed", "All good");

        vaccinationRecordService.createVaccinationRecord(record);
        verify(vaccinationRecordRepository, times(1)).addVaccinationRecord(record);
    }

    @Test
    public void getAllVaccinationRecords_shouldReturnAllRecords() {
        VaccinationRecord record1 = new VaccinationRecord(1L, 1L, "Rabies", LocalDate.now(),
                LocalDate.now().plusYears(1), "Yes", new BigDecimal("2.5"), "Dr. Smith",
                "Clinic A", "Completed", "All good");

        VaccinationRecord record2 = new VaccinationRecord(2L, 1L, "Distemper", LocalDate.now(),
                LocalDate.now().plusYears(2), "No", new BigDecimal("1.0"), "Dr. John",
                "Clinic B", "Pending", "Observation required");

        when(vaccinationRecordRepository.findAllVaccinationRecords()).thenReturn(List.of(record1, record2));

        List<VaccinationRecord> records = vaccinationRecordService.getAllVaccinationRecords();
        assertEquals(2, records.size());
        verify(vaccinationRecordRepository, times(1)).findAllVaccinationRecords();
    }
}
