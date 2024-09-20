package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.TreatmentPlan;

import java.util.List;

public interface TreatmentPlanService {
    List<TreatmentPlan> getAllTreatmentPlans();

    List<TreatmentPlan> getTreatmentPlanByPetId(Long petId);

    void createTreatmentPlan(TreatmentPlan treatmentPlan);


}
