package au.edu.rmit.sept.webapp.models;

public class User {


    private String password;
    private String email;
    private String name;
    private Long phoneNumber;
    private String address;
    private Long id;
    private String userType;

    public User(Long id, String name, String email, String password, Long phoneNumber, String address, String userType) {
        this.id = id;
        this.name = name;
        this.email=email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.userType = userType;
    }

    public User() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName(){
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
    }

}