package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.MedicalHistory;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.sql.DataSource;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MedicalHistoryRepositoryImplTest {

    @Autowired
    private Flyway flyway;

    @Autowired
    private DataSource dataSource;

    private MedicalHistoryRepository repository;

    @BeforeEach
    public void setUp() {
        flyway.migrate();
        repository = new MedicalHistoryRepositoryImpl(dataSource);
    }

    @AfterEach
    public void tearDown() {
        flyway.clean();
    }

    @Test
    public void findAllMedicalHistories_should_returnRecords() {
        List<MedicalHistory> records = repository.findAllMedicalHistories();
        assertNotNull(records);
        assertEquals(10, records.size()); // Assuming Flyway migration added 30 records
    }

    @Test
    public void findMedicalHistoryByPetId_should_returnCorrectRecords() {
        List<MedicalHistory> records = repository.findMedicalHistoryByPetId(1L);
        assertNotNull(records);
        assertEquals(1, records.size()); // Assuming pet ID 1 has 3 records
    }

    @Test
    public void addMedicalHistory_should_increaseRecordCount() {
        List<MedicalHistory> initialRecords = repository.findAllMedicalHistories();
        int initialSize = initialRecords.size();

        MedicalHistory newRecord = new MedicalHistory(
                1L, new java.util.Date(), "New Diagnosis", "New Treatment", "New Medication", "New Condition", new java.util.Date()
        );
        repository.addMedicalHistory(newRecord);

        List<MedicalHistory> updatedRecords = repository.findAllMedicalHistories();
        assertEquals(initialSize + 1, updatedRecords.size());
    }
}
