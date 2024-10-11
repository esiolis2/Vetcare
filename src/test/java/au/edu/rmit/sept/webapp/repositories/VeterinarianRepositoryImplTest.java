package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.Veterinarian;
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

public class VeterinarianRepositoryImplTest {

    @Autowired
    private Flyway flyway;

    @Autowired
    private DataSource dataSource;

    private VeterinarianRepository repository;

    @BeforeEach
    public void setUp() {
        flyway.migrate();
        repository = new VeterinarianRepositoryImpl(dataSource);
    }

    @AfterEach
    public void tearDown() {
        flyway.clean();
    }

    @Test
    public void findAllVeterinarians_should_returnAllVeterinarians() {
        List<Veterinarian> veterinarians = repository.findAllVeterinarians();
        assertNotNull(veterinarians);
        assertEquals(15, veterinarians.size());
    }

    @Test
    public void findAllVeterinariansById_should_returnCorrectVeterinarians() {
        List<Veterinarian> veterinarians = repository.findAllVeterinariansById(1L);
        assertNotNull(veterinarians);
        assertEquals(3, veterinarians.size());
    }

    @Test
    public void findAllVeterinariansById_should_returnEmptyListForUnknownClinic() {
        List<Veterinarian> veterinarians = repository.findAllVeterinariansById(999L);
        assertNotNull(veterinarians);
        assertEquals(0, veterinarians.size());
    }
}
