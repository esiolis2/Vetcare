package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.PetInformation;
import au.edu.rmit.sept.webapp.repositories.PetInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetInformationServiceImpl implements PetInformationService {

    @Autowired
    private PetInformationRepository petInformationRepository;

    @Autowired
    public PetInformationServiceImpl(PetInformationRepository petInformationRepository) {
        this.petInformationRepository = petInformationRepository;
    }

    @Override
    public List<PetInformation> getAllPets() {
        return petInformationRepository.findAll();
    }

    @Override
    public PetInformation createPetInformation(PetInformation petInformation) {
        return petInformationRepository.addPetInformation(petInformation);
    }

    @Override
    public PetInformation getPetById(Long id) {
        return petInformationRepository.findPetById(id);
    }

    @Override
    public List<PetInformation> getPetByUserId(Long userId) {
        return petInformationRepository.findPetsByOwnerId(userId);
    }
}




