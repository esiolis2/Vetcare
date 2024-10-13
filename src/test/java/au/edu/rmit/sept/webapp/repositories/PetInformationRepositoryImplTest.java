package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.PetInformation;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration
@ActiveProfiles("test")
public class PetInformationRepositoryImplTest {

    @Autowired
    private Flyway flyway;

    @Autowired
    private DataSource dataSource;
    @MockBean
    private PetInformationRepository repository;

    @BeforeEach
    public void setUp() {
        flyway.migrate();
        repository = new PetInformationRepositoryImpl(dataSource);
    }

    @AfterEach
    public void tearDown() {

        flyway.clean();
    }

    @Test
    public void findAllPets_should_returnDefaultPets() {

        List<PetInformation> pets = repository.findAll();
        assertNotNull(pets);
        assertEquals(10, pets.size());
    }

    @Test
    public void findPetById_should_returnCorrectPet() {

        PetInformation pet = repository.findPetById(1L);
        assertNotNull(pet);
        assertEquals("Buddy", pet.getName());
    }

    @Test
    public void addPetInformation_should_increasePetCount() {

        List<PetInformation> initialPets = repository.findAll();
        int initialSize = initialPets.size();


        PetInformation newPet = new PetInformation(
                11L, "Bella", 3, "Female", 15.5, "Beagle", LocalDate.of(2020, 5, 1), 1L);

        repository.addPetInformation(newPet);


        List<PetInformation> updatedPets = repository.findAll();
        assertEquals(initialSize + 1, updatedPets.size());
    }
}

