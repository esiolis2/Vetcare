package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.TreatmentPlan;
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
public class TreatmentPlanRepositoryImplTest {

    @Autowired
    private Flyway flyway;

    @Autowired
    private DataSource dataSource;

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

        PetInformation pet = new PetInformation(1L, "Max", 3, "Male", 10.5, "Golden Retriever", LocalDate.of(2018, 6, 15), "John Doe", "123-456-789");

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
}
