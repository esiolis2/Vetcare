package au.edu.rmit.sept.webapp.repositories;


import au.edu.rmit.sept.webapp.models.Clinic;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClinicRepository {
    public List<Clinic> findAll();

    public Clinic findClinicById(Long id);

}
