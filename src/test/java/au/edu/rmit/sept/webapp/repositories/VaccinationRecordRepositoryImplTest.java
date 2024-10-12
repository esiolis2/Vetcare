package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.VaccinationRecord;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest()
@Transactional
public class VaccinationRecordRepositoryImplTest {

    @Autowired
    private Flyway flyway;

    @Autowired
    private DataSource dataSource;
    @MockBean
    private VaccinationRecordRepository repository;

    @BeforeEach
    public void setUp() {
        flyway.migrate();
        repository = new VaccinationRecordRepositoryImpl(dataSource);
    }

    @AfterEach
    public void tearDown() {
        flyway.clean();
    }

    @Test
    public void findAllVaccinationRecords_should_returnDefaultRecords() {
        List<VaccinationRecord> records = repository.findAllVaccinationRecords();
        assertNotNull(records);
        assertEquals(30, records.size()); //total 30 records in your flyway migration...
    }

    @Test
    public void findVaccinationRecordByPetId_should_returnCorrectRecords() {
        List<VaccinationRecord> records = repository.findVaccinationRecordByPetId(1L);
        assertNotNull(records);
        assertEquals(3, records.size()); //pet ID 1 has 3 records in your default data..
    }

    @Test
    public void addVaccinationRecord_should_increaseRecordCount() {
        List<VaccinationRecord> initialRecords = repository.findAllVaccinationRecords();
        int initialSize = initialRecords.size();

        VaccinationRecord newRecord = new VaccinationRecord(
                null, 1L, "Rabies", LocalDate.now(), LocalDate.now().plusYears(1), "Yes",
                new BigDecimal("2.5"), "Dr. Smith", "Clinic A", "Completed", "All good"
        );
        repository.addVaccinationRecord(newRecord);

        List<VaccinationRecord> updatedRecords = repository.findAllVaccinationRecords();
        assertEquals(initialSize + 1, updatedRecords.size());
    }
}
