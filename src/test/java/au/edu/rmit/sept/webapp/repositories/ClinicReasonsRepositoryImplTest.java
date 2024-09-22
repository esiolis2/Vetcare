package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.ClinicReasons;
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
public class ClinicReasonsRepositoryImplTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private Flyway flyway;

    private ClinicReasonsRepository clinicReasonsRepository;

    @BeforeEach
    public void setUp() {
        flyway.migrate();
        clinicReasonsRepository = new ClinicReasonsRepositoryImpl(dataSource);
    }

    @AfterEach
    public void tearDown() {
        flyway.clean();
    }

    @Test
    public void testFindAll_ShouldReturnListOfClinicReasons() {
        List<ClinicReasons> reasonsList = clinicReasonsRepository.findAll();

        assertNotNull(reasonsList);
        assertFalse(reasonsList.isEmpty());
    }


}