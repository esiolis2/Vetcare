package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository {
    public User insertUserData(User user);
}
