package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.ClinicServicePricing;

import java.util.List;

public interface ClinicServicePricingService {
    public List<ClinicServicePricing> getAllById(int id);
    public List<ClinicServicePricing> getAll();

}
