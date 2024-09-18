package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.User;
import au.edu.rmit.sept.webapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


// Specifies the METHODS' functionalities
@Service
public class UserServiceImpl implements UserService{

    private UserRepository userR;


    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userR = userRepository;
    }

    @Override
    public User createUser(User user){
        return userR.insertUserData(user);
    }

    @Override
    public boolean verifuUser(String email, String password){
        User u= userR.findByEmail(email, password);
        return u!=null && u.getPassword().equals(password);
    }



}
