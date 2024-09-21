package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.Veterinarian;
import au.edu.rmit.sept.webapp.repositories.VeterinarianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeterinarianServiceImpl implements VeterinarianService{

    @Autowired
    private final VeterinarianRepository veterinarianRepository;

    @Autowired
    public VeterinarianServiceImpl(VeterinarianRepository veterinarianRepository) {
        this.veterinarianRepository = veterinarianRepository;
    }
    @Override
    public List<Veterinarian> getAllVeterinarians() {
        return veterinarianRepository.findAllVeterinarians();
    }

    @Override
    public List<Veterinarian> getVeterinarianById(Long clinicId) {
        return veterinarianRepository.findAllVeterinariansById(clinicId);
    }
}
