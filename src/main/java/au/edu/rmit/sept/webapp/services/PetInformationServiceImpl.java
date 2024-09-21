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
}



//package au.edu.rmit.sept.webapp.service;
//
//import au.edu.rmit.sept.webapp.models.PetInformation;
//import au.edu.rmit.sept.webapp.repositories.PetInformationRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class PetInformationServiceImpl implements PetInformationService {
//
//    @Autowired
//    private PetInformationRepository petInformationRepository;
//
//    @Override
//    public List<PetInformation> getAllPets() {
//        try {
//            return petInformationRepository.findAll();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException("Error fetching all pets", e);
//        }
//    }
//
//    @Override
//    public Optional<PetInformation> getPetById(Long id) {
//        try {
//            return Optional.ofNullable(petInformationRepository.findPetById(id));
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException("Error fetching pet by ID", e);
//        }
//    }
//}
