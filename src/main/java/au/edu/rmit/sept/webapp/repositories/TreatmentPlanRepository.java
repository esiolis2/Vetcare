package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.TreatmentPlan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreatmentPlanRepository {
    List<TreatmentPlan> findAll();

    List<TreatmentPlan> findByPetId(Long petId);

    void addTreatmentPlan(TreatmentPlan treatmentPlan);

    void updateTreatmentPlan(TreatmentPlan treatmentPlan);

}
