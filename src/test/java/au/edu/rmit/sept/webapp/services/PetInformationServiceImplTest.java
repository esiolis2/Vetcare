package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.PetInformation;
import au.edu.rmit.sept.webapp.repositories.PetInformationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PetInformationServiceImplTest {

    private PetInformationService petInformationService;
    private PetInformationRepository petInformationRepository;

    @BeforeEach
    public void setUp() {
        petInformationRepository = Mockito.mock(PetInformationRepository.class);
        petInformationService = new PetInformationServiceImpl(petInformationRepository);
    }

    @Test
    public void testGetAllPets_shouldReturnAllPets() {
        PetInformation pet1 = new PetInformation(1L, "Buddy", 3, "Male", 10.0, "Golden Retriever", null, "Mark Smith", "123456789");
        PetInformation pet2 = new PetInformation(2L, "Max", 4, "Male", 15.0, "German Shepherd", null, "Mark Smith", "987654321");

        when(petInformationRepository.findAll()).thenReturn(List.of(pet1, pet2));

        List<PetInformation> pets = petInformationService.getAllPets();
        assertEquals(2, pets.size());
        assertEquals("Buddy", pets.get(0).getName());
        verify(petInformationRepository, times(1)).findAll();
    }

    @Test
    public void testCreatePetInformation_shouldCallRepository() {
        PetInformation pet = new PetInformation(1L, "Buddy", 3, "Male", 10.0, "Golden Retriever", null, "John Doe", "123456789");

        when(petInformationRepository.addPetInformation(pet)).thenReturn(pet);

        PetInformation createdPet = petInformationService.createPetInformation(pet);
        assertEquals("Buddy", createdPet.getName());
        verify(petInformationRepository, times(1)).addPetInformation(pet);
    }

    @Test
    public void testGetPetById_shouldReturnPet() {
        PetInformation pet = new PetInformation(1L, "Buddy", 3, "Male", 10.0, "Golden Retriever", null, "John Doe", "123456789");

        when(petInformationRepository.findPetById(1L)).thenReturn(pet);

        PetInformation foundPet = petInformationService.getPetById(1L);
        assertEquals("Buddy", foundPet.getName());
        verify(petInformationRepository, times(1)).findPetById(1L);
    }
}
