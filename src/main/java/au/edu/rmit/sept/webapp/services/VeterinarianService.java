package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.Veterinarian;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VeterinarianService {
    public List<Veterinarian> getAllVeterinarians();
}
