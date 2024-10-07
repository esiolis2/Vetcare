//package au.edu.rmit.sept.webapp.repositories;
//
//import au.edu.rmit.sept.webapp.models.MedicalHistory;
//import org.flywaydb.core.Flyway;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.sql.DataSource;
//import java.util.Date;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class MedicalHistoryRepositoryImplTest {
//
//    @Autowired
//    private Flyway flyway;
//
//    @Autowired
//    private DataSource dataSource;
//
//    private MedicalHistoryRepository repository;
//
//    @BeforeEach
//    public void setUp() {
//        flyway.migrate();
//        repository = new MedicalHistoryRepositoryImpl(dataSource);
//    }
//
//    @AfterEach
//    public void tearDown() {
//        flyway.clean();
//    }
//
//    @Test
//    public void findAllMedicalHistories_should_returnRecords() {
//        List<MedicalHistory> records = repository.findAllMedicalHistories();
//        assertNotNull(records);
//        assertEquals(10, records.size());
//    }
//
//    @Test
//    public void findMedicalHistoryByPetId_should_returnCorrectRecords() {
//        List<MedicalHistory> records = repository.findMedicalHistoryByPetId(1L);
//        assertNotNull(records);
//        assertEquals(1, records.size()); // Assuming pet ID 1 has 3 records
//    }
//
//    @Test
//    public void addMedicalHistory_should_increaseRecordCount() {
//        List<MedicalHistory> initialRecords = repository.findAllMedicalHistories();
//        int initialSize = initialRecords.size();
//
//        MedicalHistory newRecord = new MedicalHistory(
//                1L, new java.util.Date(), "New Diagnosis", "New Treatment", "New Medication", "New Condition", new java.util.Date()
//        );
//        repository.addMedicalHistory(newRecord);
//
//        List<MedicalHistory> updatedRecords = repository.findAllMedicalHistories();
//        assertEquals(initialSize + 1, updatedRecords.size());
//    }
//
//    @Test
//    public void findMedicalHistoryByPetId_should_returnEmptyListWhenNoRecords() {
//        List<MedicalHistory> records = repository.findMedicalHistoryByPetId(999L);
//        assertTrue(records.isEmpty());
//    }
//
//    @Test
//    public void testDefaultConstructor() {
//        MedicalHistory medicalHistory = new MedicalHistory();
//        assertNotNull(medicalHistory);
//    }
//
//    @Test
//    public void testMedicalHistoryGettersSetters() {
//        MedicalHistory medicalHistory = new MedicalHistory();
//        medicalHistory.setHistoryID(1L);
//        medicalHistory.setPetID(2L);
//        medicalHistory.setLastVisitDate(new Date());
//        medicalHistory.setLastDiagnosis("Diagnosis");
//        medicalHistory.setTreatmentProvided("Treatment");
//        medicalHistory.setMedicationsPrescribed("Medication");
//        medicalHistory.setOngoingConditions("Condition");
//        medicalHistory.setNextScheduledVisit(new Date());
//
//        assertEquals(1L, medicalHistory.getHistoryID());
//        assertEquals(2L, medicalHistory.getPetID());
//        assertEquals("Diagnosis", medicalHistory.getLastDiagnosis());
//        assertEquals("Treatment", medicalHistory.getTreatmentProvided());
//        assertEquals("Medication", medicalHistory.getMedicationsPrescribed());
//        assertEquals("Condition", medicalHistory.getOngoingConditions());
//        assertNotNull(medicalHistory.getLastVisitDate());
//        assertNotNull(medicalHistory.getNextScheduledVisit());
//    }
//
//
//
//}
