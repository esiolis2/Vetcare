package au.edu.rmit.sept.webapp.models;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class PetInformation {

    private Long petID;


    private String name;

    private Integer age;


    private String gender;


    private Double weight;


    private String breed;

    private LocalDate birthDate;


    private Long ownerId;

    public PetInformation(Long petID, String name, int age, String gender, double weight, String breed, LocalDate birthDate, Long ownerId) {
        this.petID = petID;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.breed = breed;
        this.birthDate = birthDate;
        this.ownerId = ownerId;
    }

    public PetInformation() {}

    public Long getPetID() {
        return petID;
    }

    public void setPetID(Long petID) {
        this.petID = petID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Long getOwnerId(){
        return ownerId;
    }

    public void setOwnerId(Long ownerId){
        this.ownerId = ownerId;
    }

    public String getFormattedBirthDate() {
        if (birthDate != null) {
            return birthDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        return "";
    }

}
