package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.User;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserRepositoryImplTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private Flyway flyway;

    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        flyway.migrate();
        userRepository = new UserRepositoryImpl(dataSource);
    }

    @AfterEach
    public void tearDown() {
        flyway.clean();
    }


    @Test
    public void testInsertUserData_ShouldReturnUser() {
        User user = new User();
        user.setName("Mark Smith");
        user.setAddress("123 Test Road, Melbourne");
        user.setPhoneNumber(1L);
        user.setEmail("mark.smith@example.com");
        user.setPassword("securepassword");

        User result = userRepository.insertUserData(user);

        assertNotNull(result);
        assertEquals("Mark Smith", result.getName());
        assertEquals("mark.smith@example.com", result.getEmail());
    }


    @Test
    public void testFindByEmail_ShouldReturnCorrectUser() {

        User user = new User();
        user.setName("Mark Smith");
        user.setAddress("123 Test Road, Melbourne");
        user.setPhoneNumber(1L);
        user.setEmail("mark.smith@example.com");
        user.setPassword("anotherpassword");
        userRepository.insertUserData(user);

        User foundUser = userRepository.findByEmail("mark.smith@example.com");

        assertNotNull(foundUser);
        assertEquals("Mark Smith", foundUser.getName());
    }


    @Test
    public void testFindByEmail_UserNotFound_ShouldReturnNull() {
        User foundUser = userRepository.findByEmail("nonexistent@example.com");
        assertNull(foundUser);
    }
}
