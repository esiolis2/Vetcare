package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.User;

import java.util.List;

//Interface because it needs follow certain rules or methods
public interface UserService {
    public User createUser (User u);
    public boolean verifyUser(String email, String password);
    public User findByEmail(String email);
    public User findByUser(Long id);
    public User updateUser(User user);
    public List<User> getAllUsers();
    public boolean deleteUser(String email);
}

