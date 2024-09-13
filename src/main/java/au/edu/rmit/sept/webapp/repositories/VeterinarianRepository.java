package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.Veterinarian;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeterinarianRepository {
    public List<Veterinarian> findAllVeterinarians();
}
