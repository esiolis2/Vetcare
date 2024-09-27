package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.User;

//Interface because it needs follow certain rules or methods
public interface UserService {
    public User createUser (User u);
    public boolean verifyUser(String email, String password);
    public User findByEmail(String email);
    public User findByUser(Long id);
//    public List<User> findAll();
}

