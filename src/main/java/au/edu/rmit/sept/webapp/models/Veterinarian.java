package au.edu.rmit.sept.webapp.models;

public class Veterinarian {
    Long id;
    String name;
    String email;
    Long clinicId;

    public Veterinarian(Long id, String name, String email, Long clinicId){
        this.name = name;
        this.clinicId = clinicId;
        this.email = email;
        this.id = id;
    }

    public Veterinarian(){}

    //Setters and getters

    public void setId(Long id){
        this.id = id;
    }
    public Long getId(){
        return this.id;
    }

    public void setName(String name){
        this.name =name;
    }
    public String getName(){
        return this.name;
    }

    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return this.email;
    }

    public void setClinicId(Long clinicId){
        this.clinicId = clinicId;
    }
    public Long getClinicId(){
        return this.clinicId;
    }
}
