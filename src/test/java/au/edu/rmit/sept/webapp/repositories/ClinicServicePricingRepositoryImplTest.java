package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.ClinicServicePricing;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
@Transactional
public class ClinicServicePricingRepositoryImplTest {

    @Autowired
    private Flyway flyway;

    @Autowired
    private DataSource dataSource;
    @MockBean
    private ClinicServicePricingRepository repository;

    @BeforeEach
    public void setUp() {
        flyway.migrate();
        repository = new ClinicServicePricingRepositoryImpl(dataSource);
    }

    @AfterEach
    public void tearDown() {
        flyway.clean();
    }


    @Test
    public void findAll_should_returnAllPricing() {
        List<ClinicServicePricing> pricingList = repository.findAll();
        assertNotNull(pricingList);
        assertTrue(pricingList.size() > 0);
        assertEquals("Healthy Paws Clinic", pricingList.get(1).getClinicName());
    }
}
