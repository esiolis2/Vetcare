package au.edu.rmit.sept.webapp.models;

public class User {

    private String username;
    private String password;
    private String email;
    private String name;
    private Integer id;

    public User(Integer id, String name, String username, String email, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email=email;
        this.password = password;

    }

    public User() {}


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void setEmail(String email){
        this.email=email;
    }

    public String getEmail(){
        return email;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }


}
