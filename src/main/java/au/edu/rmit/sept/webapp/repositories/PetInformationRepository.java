package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.PetInformation;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface PetInformationRepository {
    public List<PetInformation> findAll();
    public PetInformation addPetInformation(PetInformation petInformation);
    public PetInformation findPetById(Long id);

}