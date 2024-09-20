package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.ClinicReasons;

import java.util.List;

public interface ClinicReasonsRepository {
    public List<ClinicReasons> findAll();
}
