package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.models.User;
import au.edu.rmit.sept.webapp.services.PetInformationService;
import au.edu.rmit.sept.webapp.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
@ActiveProfiles("test")
public class UserControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @MockBean
    private PetInformationService petInfoService;

    private User user;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        user = new User(1L, "Mark", "test@example.com", "password", 1234567890L, "123 Example St", "User");
    }

    @Test
    public void testCreateUser_Success() throws Exception {
        when(userService.createUser(Mockito.any(User.class))).thenReturn(user);
        mockMvc.perform(post("/signup")
                        .flashAttr("user", user))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void testLogin_Success() throws Exception {
        when(userService.verifyUser("test@example.com", "password")).thenReturn(true);
        when(userService.findByEmail("test@example.com")).thenReturn(user);
        mockMvc.perform(post("/login")
                        .param("email", "test@example.com")
                        .param("password", "password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void testLogin_Failure() throws Exception {
        when(userService.verifyUser("test@example.com", "wrongPassword")).thenReturn(false);
        mockMvc.perform(post("/login")
                        .param("email", "test@example.com")
                        .param("password", "wrongPassword"))
                .andExpect(status().isOk())
                .andExpect(view().name("Login"))
                .andExpect(model().attribute("errorMessage", "Invalid email or password"));
    }

    @Test
    public void testGetUserProfile_Success() throws Exception {
        when(userService.findByEmail("test@example.com")).thenReturn(user);
        mockMvc.perform(get("/account")
                        .sessionAttr("loggedInUser", user))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attribute("user", user))
                .andExpect(view().name("Account"));
    }

    @Test
    public void testLogout() throws Exception {
        mockMvc.perform(post("/logout")
                        .sessionAttr("loggedInUser", user))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void testEditUser_Success() throws Exception {
        User updatedUser = new User(1L, "Mark Updated", "test@example.com", "password", 1234567890L, "123 Example St", "User");
        when(userService.updateUser(Mockito.any(User.class))).thenReturn(updatedUser);
        mockMvc.perform(post("/account/edit")
                        .sessionAttr("loggedInUser", user)
                        .flashAttr("user", updatedUser))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void testDeleteUser_Success() throws Exception {
        when(userService.deleteUser("test@example.com")).thenReturn(true);
        mockMvc.perform(post("/account/delete")
                        .sessionAttr("loggedInUser", user))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

}