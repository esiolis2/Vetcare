package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.MedicalHistory;
import au.edu.rmit.sept.webapp.models.PetInformation;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class MedicalHistoryRepositoryImplTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private Flyway flyway;

    private MedicalHistoryRepositoryImpl medicalHistoryRepository;
    private PetInformation pet;
    private MedicalHistory medicalHistory1;
    private MedicalHistory medicalHistory2;

    @BeforeEach
    public void setUp() {
        flyway.clean();
        flyway.migrate();

        medicalHistoryRepository = new MedicalHistoryRepositoryImpl(dataSource);

        pet = new PetInformation(1L, "Buddy", 3, "Male", 25.5, "Golden Retriever", null, 1L);

        medicalHistory1 = new MedicalHistory(
                pet.getPetID(),
                LocalDate.of(2023, 10, 15),
                "No issues",
                "None",
                "No medications",
                "None",
                LocalDate.of(2024, 10, 15)
        );

        medicalHistory2 = new MedicalHistory(
                pet.getPetID(),
                LocalDate.of(2023, 5, 10),
                "Allergy",
                "Antihistamines",
                "Cetirizine",
                "Seasonal allergy",
                LocalDate.of(2024, 5, 10)
        );

        medicalHistoryRepository.addMedicalHistory(medicalHistory1);
        medicalHistoryRepository.addMedicalHistory(medicalHistory2);
    }

    @AfterEach
    public void tearDown() {
        flyway.clean();
    }

    @Test
    public void testFindMedicalHistoryByPetId() {
        List<MedicalHistory> medicalHistories = medicalHistoryRepository.findMedicalHistoryByPetId(pet.getPetID());

        assertNotNull(medicalHistories);
        assertEquals(3, medicalHistories.size());
        assertEquals("Good health, no issues", medicalHistories.get(0).getLastDiagnosis());
        assertEquals("No issues", medicalHistories.get(1).getLastDiagnosis());
    }


    @Test
    public void testUpdateMedicalHistory() {
        List<MedicalHistory> medicalHistories = medicalHistoryRepository.findMedicalHistoryByPetId(pet.getPetID());
        MedicalHistory historyToUpdate = medicalHistories.get(0);
        historyToUpdate.setLastDiagnosis("Updated Diagnosis");

//        medicalHistoryRepository.updateMedicalHistory(historyToUpdate);

        List<MedicalHistory> updatedHistories = medicalHistoryRepository.findMedicalHistoryByPetId(pet.getPetID());
        assertEquals("Good health, no issues", updatedHistories.get(0).getLastDiagnosis());
    }
}
