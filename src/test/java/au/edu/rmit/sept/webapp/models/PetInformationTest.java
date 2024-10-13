package au.edu.rmit.sept.webapp.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PetInformationTest {

    private PetInformation petInformation;

    @BeforeEach
    public void setUp() {
        petInformation = new PetInformation();
    }

    @Test
    public void testNoArgsConstructor() {
        assertNotNull(petInformation);
    }

    @Test
    public void testAllArgsConstructor() {
        Long petID = 1L;
        String name = "Buddy";
        int age = 5;
        String gender = "Male";
        double weight = 12.5;
        String breed = "Golden Retriever";
        LocalDate birthDate = LocalDate.of(2018, 5, 12);
        Long ownerId = 2L;

        PetInformation petInformation = new PetInformation(petID, name, age, gender, weight, breed, birthDate, ownerId);

        assertEquals(petID, petInformation.getPetID());
        assertEquals(name, petInformation.getName());
        assertEquals(age, petInformation.getAge());
        assertEquals(gender, petInformation.getGender());
        assertEquals(weight, petInformation.getWeight());
        assertEquals(breed, petInformation.getBreed());
        assertEquals(birthDate, petInformation.getBirthDate());
        assertEquals(ownerId, petInformation.getOwnerId());
    }

    @Test
    public void testSettersAndGetters() {
        Long petID = 1L;
        String name = "Lucy";
        int age = 3;
        String gender = "Female";
        double weight = 10.3;
        String breed = "Beagle";
        LocalDate birthDate = LocalDate.of(2020, 7, 19);
        Long ownerId = 3L;

        petInformation.setPetID(petID);
        petInformation.setName(name);
        petInformation.setAge(age);
        petInformation.setGender(gender);
        petInformation.setWeight(weight);
        petInformation.setBreed(breed);
        petInformation.setBirthDate(birthDate);
        petInformation.setOwnerId(ownerId);

        assertEquals(petID, petInformation.getPetID());
        assertEquals(name, petInformation.getName());
        assertEquals(age, petInformation.getAge());
        assertEquals(gender, petInformation.getGender());
        assertEquals(weight, petInformation.getWeight());
        assertEquals(breed, petInformation.getBreed());
        assertEquals(birthDate, petInformation.getBirthDate());
        assertEquals(ownerId, petInformation.getOwnerId());
    }

    @Test
    public void testGetFormattedBirthDate() {
        LocalDate birthDate = LocalDate.of(2017, 11, 25);
        petInformation.setBirthDate(birthDate);

        String formattedDate = petInformation.getFormattedBirthDate();

        assertEquals("2017-11-25", formattedDate);
    }

    @Test
    public void testGetFormattedBirthDate_NullDate() {
        petInformation.setBirthDate(null);
        assertEquals("", petInformation.getFormattedBirthDate());
    }
}
