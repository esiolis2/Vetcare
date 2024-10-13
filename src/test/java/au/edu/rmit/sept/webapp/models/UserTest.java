package au.edu.rmit.sept.webapp.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
    }

    @Test
    public void testNoArgsConstructor() {
        assertNotNull(user);
    }

    @Test
    public void testAllArgsConstructor() {
        Long id = 1L;
        String name = "John Doe";
        String email = "john.doe@example.com";
        String password = "password123";
        Long phoneNumber = 123456789L;
        String address = "123 Main St";
        String userType = "admin";

        User user = new User(id, name, email, password, phoneNumber, address, userType);

        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(phoneNumber, user.getPhoneNumber());
        assertEquals(address, user.getAddress());
        assertEquals(userType, user.getUserType());
    }

    @Test
    public void testSettersAndGetters() {
        Long id = 2L;
        String name = "Jane Smith";
        String email = "jane.smith@example.com";
        String password = "password321";
        Long phoneNumber = 987654321L;
        String address = "456 Elm St";
        String userType = "customer";

        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);
        user.setAddress(address);
        user.setUserType(userType);

        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(phoneNumber, user.getPhoneNumber());
        assertEquals(address, user.getAddress());
        assertEquals(userType, user.getUserType());
    }

    @Test
    public void testEmptyFields() {
        assertNull(user.getId());
        assertNull(user.getName());
        assertNull(user.getEmail());
        assertNull(user.getPassword());
        assertNull(user.getPhoneNumber());
        assertNull(user.getAddress());
        assertNull(user.getUserType());
    }

    @Test
    public void testUpdateUserDetails() {
        user.setName("Alice Johnson");
        user.setEmail("alice.johnson@example.com");
        user.setPhoneNumber(555555555L);
        user.setAddress("789 Pine St");

        assertEquals("Alice Johnson", user.getName());
        assertEquals("alice.johnson@example.com", user.getEmail());
        assertEquals(555555555L, user.getPhoneNumber());
        assertEquals("789 Pine St", user.getAddress());
    }
}
