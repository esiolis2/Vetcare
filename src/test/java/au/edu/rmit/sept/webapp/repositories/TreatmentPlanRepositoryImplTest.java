package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.PetInformation;
import au.edu.rmit.sept.webapp.models.TreatmentPlan;
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
public class TreatmentPlanRepositoryImplTest {

    @Autowired
    private Flyway flyway;

    @Autowired
    private DataSource dataSource;
    @MockBean
    private TreatmentPlanRepository repository;

    @BeforeEach
    public void setUp() {
        flyway.migrate();
        repository = new TreatmentPlanRepositoryImpl(dataSource);
    }

    @AfterEach
    public void tearDown() {
        flyway.clean();
    }

    @Test
    public void findAllTreatmentPlans_should_returnDefaultPlans() {
        List<TreatmentPlan> plans = repository.findAll();
        assertNotNull(plans);
        assertEquals(10, plans.size());
    }

    @Test
    public void findTreatmentPlanByPetId_should_returnCorrectPlans() {
        List<TreatmentPlan> plans = repository.findByPetId(1L);
        assertNotNull(plans);
        assertEquals(1, plans.size());
    }

    @Test
    public void addTreatmentPlan_should_increasePlanCount() {
        List<TreatmentPlan> initialPlans = repository.findAll();
        int initialSize = initialPlans.size();

        PetInformation pet = new PetInformation(1L, "Max", 3, "Male", 10.5, "Golden Retriever", LocalDate.of(2018, 6, 15),1L);

        TreatmentPlan newPlan = new TreatmentPlan(
                null, "Dental Surgery", "Surgery", "Details about dental surgery", "Recovering",
                true, LocalDate.of(2023, 9, 10), LocalDate.of(2023, 9, 24), "Painkillers, Antibiotics",
                "2 weeks recovery", "Check-up in 2 weeks", "Dr. Emily Carter",
                LocalDate.of(2023, 9, 24), 350.0, "Healing as expected", "Notes about surgery",
                "City Vet Clinic", true, "VetPlan Insurance", "Paid", null, null
        );
        newPlan.setPet(pet);

        repository.addTreatmentPlan(newPlan);

        List<TreatmentPlan> updatedPlans = repository.findAll();
        assertEquals(initialSize + 1, updatedPlans.size());
    }

    @Test
    public void updateTreatmentPlan_shouldUpdatePlanSuccessfully() {
        PetInformation pet = new PetInformation(1L, "Max", 3, "Male", 10.5, "Golden Retriever", LocalDate.of(2018, 6, 15), 1L);

        TreatmentPlan existingPlan = new TreatmentPlan(
                null, "Dental Surgery", "Surgery", "Details about dental surgery", "Recovering",
                true, LocalDate.of(2023, 9, 10), LocalDate.of(2023, 9, 24), "Painkillers, Antibiotics",
                "2 weeks recovery", "Check-up in 2 weeks", "Dr. Emily Carter",
                LocalDate.of(2023, 9, 24), 350.0, "Healing as expected", "Notes about surgery",
                "City Vet Clinic", true, "VetPlan Insurance", "Paid", null, null
        );
        existingPlan.setPet(pet);

        repository.addTreatmentPlan(existingPlan);
        List<TreatmentPlan> plans = repository.findAll();
        TreatmentPlan planToUpdate = plans.get(plans.size() - 1);

        planToUpdate.setDiagnosis("Updated Diagnosis");
        planToUpdate.setTreatmentDescription("Updated treatment details");
        repository.updateTreatmentPlan(planToUpdate);

        TreatmentPlan updatedPlan = repository.findByPetId(pet.getPetID()).get(0);
        assertEquals("Dental Care", updatedPlan.getDiagnosis());
        assertEquals("Regular checkup and dental cleaning", updatedPlan.getTreatmentDescription());
    }

    @Test
    public void findTreatmentPlanByPetId_shouldReturnEmptyListWhenNoPlansExist() {
        List<TreatmentPlan> plans = repository.findByPetId(999L);
        assertNotNull(plans);
        assertEquals(0, plans.size());
    }


    @Test
    public void addTreatmentPlan_shouldAddWithMinimalInformation() {
        PetInformation pet = new PetInformation(2L, "Shadow", 4, "Female", 20.0, "Labrador", LocalDate.of(2020, 3, 22), 2L);

        TreatmentPlan minimalPlan = new TreatmentPlan(
                null, "Basic Checkup", "Routine", "General health checkup", "Stable", false,
                LocalDate.of(2024, 2, 15), null, null, null, null, "Dr. Andrew Brown",
                null, 0.0, null, null, "Downtown Vet Clinic", false, null, "Unpaid", null, null
        );
        minimalPlan.setPet(pet);

        repository.addTreatmentPlan(minimalPlan);

        List<TreatmentPlan> updatedPlans = repository.findByPetId(pet.getPetID());
        assertEquals(2, updatedPlans.size());
        assertEquals("Flea Prevention", updatedPlans.get(0).getDiagnosis());
    }


}
