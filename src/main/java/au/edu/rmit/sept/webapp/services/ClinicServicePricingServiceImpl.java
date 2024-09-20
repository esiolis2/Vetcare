package au.edu.rmit.sept.webapp.services;


import au.edu.rmit.sept.webapp.models.ClinicServicePricing;
import au.edu.rmit.sept.webapp.repositories.ClinicServicePricingRepository;
import au.edu.rmit.sept.webapp.repositories.ClinicServicePricingRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicServicePricingServiceImpl implements ClinicServicePricingService {

    @Autowired
    private ClinicServicePricingRepository clinicServicePricingRepository;

    @Autowired
    ClinicServicePricingServiceImpl(ClinicServicePricingRepository clinicServicePricingRepository){
        this.clinicServicePricingRepository = clinicServicePricingRepository;
    }

    @Override
    public List<ClinicServicePricing> getAllById(int id) {
        return clinicServicePricingRepository.findAllById(id);
    }

    @Override
    public List<ClinicServicePricing> getAll() {
        return clinicServicePricingRepository.findAll();
    }
}
