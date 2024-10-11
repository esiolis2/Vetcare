package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.PrescriptionRefillRequest;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PrescriptionRefillRepositoryImplTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private Flyway flyway;

    private PrescriptionRefillRepositoryImpl prescriptionRefillRepository;

    private PrescriptionRefillRequest refillRequest;

    @BeforeEach
    public void setUp() {
        flyway.migrate();
        prescriptionRefillRepository = new PrescriptionRefillRepositoryImpl(dataSource);

        refillRequest = new PrescriptionRefillRequest(
                1L,
                1L,
                List.of("AllerPet 10mg", "Fidozol 50mg"),
                "123 Main St",
                "Springfield",
                "IL",
                "62701",
                "Take with food",
                LocalDateTime.now()
        );
    }

    @AfterEach
    public void tearDown() {
        flyway.clean();
    }

    @Test
    public void testSaveRefillRequest() throws SQLException {
        prescriptionRefillRepository.saveRefillRequest(refillRequest);

        List<PrescriptionRefillRequest> refillRequests = prescriptionRefillRepository.findAllRefillRequests();
        assertNotNull(refillRequests);
        assertEquals(1, refillRequests.size());
        assertEquals("AllerPet 10mg", refillRequests.get(0).getMedications().get(0));
        assertEquals("Fidozol 50mg", refillRequests.get(0).getMedications().get(1));
        assertEquals("123 Main St", refillRequests.get(0).getAddress());
    }

    @Test
    public void testFindAllRefillRequests() {
        List<PrescriptionRefillRequest> refillRequests = prescriptionRefillRepository.findAllRefillRequests();
        assertNotNull(refillRequests);
        assertEquals(0, refillRequests.size());

        prescriptionRefillRepository.saveRefillRequest(refillRequest);

        refillRequests = prescriptionRefillRepository.findAllRefillRequests();
        assertEquals(1, refillRequests.size());
        assertEquals("AllerPet 10mg", refillRequests.get(0).getMedications().get(0));
        assertEquals("Fidozol 50mg", refillRequests.get(0).getMedications().get(1));
    }
}
