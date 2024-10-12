package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.TreatmentPlan;
import au.edu.rmit.sept.webapp.models.User;
import au.edu.rmit.sept.webapp.repositories.TreatmentPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreatmentPlanServiceImpl implements TreatmentPlanService {

    private final TreatmentPlanRepository treatmentPlanRepository;

    @Autowired
    public TreatmentPlanServiceImpl(TreatmentPlanRepository treatmentPlanRepository) {
        this.treatmentPlanRepository = treatmentPlanRepository;
    }

    @Override
    public List<TreatmentPlan> getAllTreatmentPlans() {
        return treatmentPlanRepository.findAll();
    }

    @Override
    public List<TreatmentPlan> getTreatmentPlanByPetId(Long petId) {
        return treatmentPlanRepository.findByPetId(petId);
    }


    @Override
    public void createTreatmentPlan(TreatmentPlan treatmentPlan) {
        treatmentPlanRepository.addTreatmentPlan(treatmentPlan);
    }
    @Override
    public void updateTreatmentPlan(TreatmentPlan treatmentPlan, User loggedInUser){
        if (!"Vet".equals(loggedInUser.getUserType())) {
            treatmentPlan.setPet(null);
        }

        treatmentPlanRepository.updateTreatmentPlan(treatmentPlan);
    }
}
