package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.ClinicServicePricing;

import java.util.List;

public interface ClinicServicePricingRepository {

    public List<ClinicServicePricing> findAllById(int id);
    public List<ClinicServicePricing> findAll();
}
