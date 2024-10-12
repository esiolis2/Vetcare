package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.PetInformation;
import au.edu.rmit.sept.webapp.models.Prescription;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest()
@Transactional
public class PrescriptionRepositoryImplTest {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private Flyway flyway;
    @MockBean
    private PrescriptionRepositoryImpl prescriptionRepository;
    @MockBean
    private Prescription prescription1;

    @MockBean
    private PetInformation pet;

    @BeforeEach
    public void setUp() {
        flyway.migrate();
        prescriptionRepository = new PrescriptionRepositoryImpl(dataSource);

        pet = new PetInformation(1L, "Buddy", 3, "Male", 25.5, "Golden Retriever", null, 1L);

        prescription1 = new Prescription(
                1L,
                "Fidozol 50mg",
                "1 tablet twice a day",
                "Take with food",
                LocalDate.of(2024, 10, 15),
                30,
                2,
                LocalDate.of(2025, 10, 15),
                pet,
                LocalDate.now()
        );

    }

    @AfterEach
    public void tearDown() {
        flyway.clean();
    }

    @Test
    public void testFindAllPrescriptions() {
        List<Prescription> prescriptions = prescriptionRepository.findAll();

        assertNotNull(prescriptions);
        assertEquals(22, prescriptions.size());
        assertEquals("Fidozol 50mg", prescriptions.get(0).getMedicationName());
        assertEquals("AllerPet 10mg", prescriptions.get(1).getMedicationName());
    }

    @Test
    public void testFindByPetId() {
        List<Prescription> prescriptions = prescriptionRepository.findByPetId(1L);

        assertNotNull(prescriptions);
        assertEquals(3, prescriptions.size());
        assertEquals("Fidozol 50mg", prescriptions.get(0).getMedicationName());
    }

    @Test
    public void testAddPrescription() {
        List<Prescription> initialPrescriptions = prescriptionRepository.findAll();
        int initialSize = initialPrescriptions.size();

        prescriptionRepository.addPrescription(prescription1);

        List<Prescription> updatedPrescriptions = prescriptionRepository.findAll();
        assertEquals(initialSize + 1, updatedPrescriptions.size());
    }
}
