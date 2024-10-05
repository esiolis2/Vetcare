package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.TreatmentPlan;
import au.edu.rmit.sept.webapp.models.User;

import java.util.List;

public interface TreatmentPlanService {
    List<TreatmentPlan> getAllTreatmentPlans();

    List<TreatmentPlan> getTreatmentPlanByPetId(Long petId);
    List<TreatmentPlan> getTreatmentPlansByUserId(Long userId);

    void createTreatmentPlan(TreatmentPlan treatmentPlan);


    void updateTreatmentPlan(TreatmentPlan treatmentPlan, User loggedInUser);
}
