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
//        user.setUsername("mark.smith");
        user.setEmail("mark.smith@example.com");
        user.setPassword("securepassword");
        user.setAddress("");
        user.setPhoneNumber(123446L);
        user.setUserType("User");

        User result = userRepository.insertUserData(user);

        assertNotNull(result);
        assertEquals("Mark Smith", result.getName());
        assertEquals("mark.smith@example.com", result.getEmail());
    }


    @Test
    public void testFindByEmail_ShouldReturnCorrectUser() {

        User user = new User();
        user.setName("Mark Smith");
        user.setEmail("mark.smith@example.com");
        user.setPassword("anotherpassword");
        user.setAddress("");
        user.setPhoneNumber(123456L);
        user.setUserType("User");
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

    @Test
    public void testFindById_shouldReturnCorrectUser(){
        User user = new User();

        user.setName("Mark Smith");
        user.setEmail("mark.121@example.com");
        user.setPassword("anotherpassword");
        user.setAddress("");
        user.setPhoneNumber(123456L);
        user.setUserType("User");
        userRepository.insertUserData(user);

        User found = userRepository.findUserById(1L);

        assertNotNull(found);
        assertEquals("Mark Smith", found.getName());
    }



    // UserRepositoryImplTest.java
    @Test
    public void testUpdateUser_shouldReturnCorrectUser() {
        User user = new User();
        user.setId(100L);
        user.setName("Mark Smith");
        user.setEmail("mark.smith@example.com");
        user.setPassword("anotherpassword");
        user.setAddress("");
        user.setPhoneNumber(123456L);
        user.setUserType("User");


        User insertedUser = userRepository.insertUserData(user);


        insertedUser.setName("Mark Updated");
        insertedUser.setId(100L);

        User updated = userRepository.updateUser(insertedUser);

        assertNotNull(updated);
        assertEquals("Mark Updated", updated.getName());
        assertEquals("mark.smith@example.com", updated.getEmail());
    }

    @Test
    public void testRemoveUser_ShouldReturnTrue() {
        User user = new User();
        user.setName("Mark Smith");
        user.setEmail("mark.smith@example.com");
        user.setPassword("securepassword");
        user.setAddress("");
        user.setPhoneNumber(123456L);
        user.setUserType("User");

        userRepository.insertUserData(user);

        boolean result = userRepository.removeUser("mark.smith@example.com");

        assertTrue(result);
    }



}
