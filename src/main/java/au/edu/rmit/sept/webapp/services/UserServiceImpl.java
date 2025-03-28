package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.User;
import au.edu.rmit.sept.webapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


// Specifies the METHODS' functionalities
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userR;
    private final PetInformationService petInformationService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PetInformationService petInformationService){
        this.userR = userRepository;
        this.petInformationService = petInformationService;
    }

    @Override
    public User createUser(User user){
        return userR.insertUserData(user);
    }

    @Override
    public boolean verifyUser(String email, String password){
        User u= userR.findByEmail(email);
//      after getting the email checking the password
        return u!=null && u.getPassword().equals(password);
    }

    @Override
    public User findByEmail(String email){
        return userR.findByEmail(email);
    }


    @Override
    public User updateUser(User user) {
        return userR.updateUser(user);
    }
    @Override
    public User findByUser(Long id) {
        return userR.findUserById(id);
    }

    public List<User> getAllUsers() {
        return userR.findAllUsers();
    }

    @Override
    public boolean deleteUser(String email) {
        return userR.removeUser(email);
    }

}
