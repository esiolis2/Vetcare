package au.edu.rmit.sept.webapp.services;

import au.edu.rmit.sept.webapp.models.User;
import au.edu.rmit.sept.webapp.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    private UserServiceImpl userService;
    private UserRepository userRepository;
    private PetInformationService petInformationService;

    @BeforeEach
    public void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        petInformationService = Mockito.mock(PetInformationService.class);
        userService = new UserServiceImpl(userRepository, petInformationService);
    }

    @Test
    public void testCreateUser_ShouldReturnUser() {
        User user = new User();
        user.setName("Mark Smith");
        user.setEmail("mark.smith@example.com");
        user.setPassword("securepassword");

        when(userRepository.insertUserData(user)).thenReturn(user);

        User createdUser = userService.createUser(user);

        assertNotNull(createdUser);
        assertEquals("Mark Smith", createdUser.getName());
        assertEquals("mark.smith@example.com", createdUser.getEmail());
        verify(userRepository, times(1)).insertUserData(user);
    }

    @Test
    public void testVerifyUser_ShouldReturnTrueForValidUser() {
        User user = new User();
        user.setEmail("mark.smith@example.com");
        user.setPassword("password123");

        when(userRepository.findByEmail("mark.smith@example.com")).thenReturn(user);

        boolean isValid = userService.verifyUser("mark.smith@example.com", "password123");

        assertTrue(isValid);
        verify(userRepository, times(1)).findByEmail("mark.smith@example.com");
    }

    @Test
    public void testVerifyUser_ShouldReturnFalseForInvalidPassword() {
        User user = new User();
        user.setEmail("mark.smith@example.com");
        user.setPassword("password123");

        when(userRepository.findByEmail("mark.smith@example.com")).thenReturn(user);

        boolean isValid = userService.verifyUser("mark.smith@example.com", "wrongpassword");

        assertFalse(isValid);
        verify(userRepository, times(1)).findByEmail("mark.smith@example.com");
    }

    @Test
    public void testVerifyUser_ShouldReturnFalseForNonExistentUser() {
        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(null);

        boolean isValid = userService.verifyUser("nonexistent@example.com", "password123");

        assertFalse(isValid);
        verify(userRepository, times(1)).findByEmail("nonexistent@example.com");
    }

    @Test
    public void testFindByEmail_ShouldReturnCorrectUser() {
        User user = new User();
        user.setEmail("mark.smith@example.com");

        when(userRepository.findByEmail("mark.smith@example.com")).thenReturn(user);

        User foundUser = userService.findByEmail("mark.smith@example.com");

        assertNotNull(foundUser);
        assertEquals("mark.smith@example.com", foundUser.getEmail());
        verify(userRepository, times(1)).findByEmail("mark.smith@example.com");
    }

    @Test
    public void testFindByEmail_ShouldReturnNullForNonExistentUser() {
        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(null);

        User foundUser = userService.findByEmail("nonexistent@example.com");

        assertNull(foundUser);
        verify(userRepository, times(1)).findByEmail("nonexistent@example.com");
    }

    @Test
    public void testFindById_ShouldReturnCorrectUser() {
        User user = new User();
        user.setId(1L);
        user.setName("Mark");

        when(userRepository.findUserById(1L)).thenReturn(user);

        User found = userService.findByUser(1L);

        assertNotNull(found);
        assertEquals("Mark", found.getName());
        verify(userRepository, times(1)).findUserById(1L);
    }

    @Test
    public void testUpdateUser_shouldReturnCorrect() {
        User user = new User();
        user.setId(1L);
        user.setName("Mark");

        when(userRepository.findUserById(1L)).thenReturn(user);
        when(userRepository.updateUser(user)).thenReturn(user);

        User found = userService.findByUser(1L);
        assertNotNull(found);
        assertEquals("Mark", found.getName());
        verify(userRepository, times(1)).findUserById(1L);

        // Update user details
        found.setName("Mark Updated");
        User updatedUser = userService.updateUser(found);

        assertNotNull(updatedUser);
        assertEquals("Mark Updated", updatedUser.getName());
        verify(userRepository, times(1)).updateUser(found);
    }


    @Test
    public void testDeleteUser_ShouldReturnTrue() {
        String email = "mark.smith@example.com";

        when(userRepository.removeUser(email)).thenReturn(true);

        boolean result = userService.deleteUser(email);

        assertTrue(result);
        verify(userRepository, times(1)).removeUser(email);
    }



}