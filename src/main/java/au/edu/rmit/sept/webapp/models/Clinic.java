package au.edu.rmit.sept.webapp.models;

public class Clinic {
    Long id;
    String name;
    String address;
    String phone;

    public Clinic() {}

    public Clinic(Long id, String name, String address, String phone){
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;

    }

    public Clinic(Long id, String name, String address, String phone, String email) {
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address =address;
    }

    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
}
