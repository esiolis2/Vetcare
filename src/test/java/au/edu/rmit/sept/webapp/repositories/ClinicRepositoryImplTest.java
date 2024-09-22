package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.Clinic;
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
public class ClinicRepositoryImplTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private Flyway flyway;

    private ClinicRepository clinicRepository;

    @BeforeEach
    public void setUp() {
        flyway.migrate();
        clinicRepository = new ClinicRepositoryImpl(dataSource);
    }

    @AfterEach
    public void tearDown() {
        flyway.clean();
    }

    @Test
    public void testFindAll_ShouldReturnListOfClinics() {
        List<Clinic> clinics = clinicRepository.findAll();

        assertNotNull(clinics);
        assertFalse(clinics.isEmpty());
    }



    @Test
    public void testFindClinicById_ShouldReturnNullForNonExistentClinic() {
        Clinic foundClinic = clinicRepository.findClinicById(999L);

        assertNull(foundClinic);
    }


}