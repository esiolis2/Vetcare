package au.edu.rmit.sept.webapp.services;
import au.edu.rmit.sept.webapp.models.PetInformation;
import java.util.List;
import java.util.Optional;


public interface PetInformationService {
    public List<PetInformation> getAllPets();

    public PetInformation createPetInformation(PetInformation petInformation);

    public PetInformation getPetById(Long id);

    public List<PetInformation> getPetByUserId(Long userId);

}
