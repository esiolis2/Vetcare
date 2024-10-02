//package au.edu.rmit.sept.webapp.controllers;
//
//import au.edu.rmit.sept.webapp.models.User;
//import au.edu.rmit.sept.webapp.services.UserService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(UserController.class)
//public class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UserService userService;
//
//    private User user;
//
//    @BeforeEach
//    public void setUp() {
//        user = new User(1L, "Mark", "test@example.com", "password", 1234567890L, "123 Example St");
//    }
//
//    @Test
//    public void testCreateUser_Success() throws Exception {
//        Mockito.when(userService.createUser(Mockito.any(User.class))).thenReturn(user);
//
//        mockMvc.perform(post("/signup")
//                        .flashAttr("user", user))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/login"));
//    }
//
//    @Test
//    public void testLogin_Success() throws Exception {
//        Mockito.when(userService.verifyUser("test@example.com", "password")).thenReturn(true);
//        Mockito.when(userService.findByEmail("test@example.com")).thenReturn(user);
//
//        mockMvc.perform(post("/login")
//                        .param("email", "test@example.com")
//                        .param("password", "password"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.success").value(true))
//                .andExpect(jsonPath("$.message").value("Login successful"));
//    }
//
//    @Test
//    public void testLogin_Failure() throws Exception {
//        Mockito.when(userService.verifyUser("test@example.com", "wrongPassword")).thenReturn(false);
//
//        mockMvc.perform(post("/login")
//                        .param("email", "test@example.com")
//                        .param("password", "wrongPassword"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.success").value(false))
//                .andExpect(jsonPath("$.message").value("Invalid email or password"));
//    }
//
//    @Test
//    public void testGetUserProfile_Success() throws Exception {
//        Mockito.when(userService.findByEmail("test@example.com")).thenReturn(user);
//
//        mockMvc.perform(get("/account")
//                        .sessionAttr("userEmail", "test@example.com"))
//                .andExpect(status().isOk())
//                .andExpect(model().attributeExists("user"))
//                .andExpect(model().attribute("user", user))
//                .andExpect(view().name("account"));
//    }
//
//    @Test
//    public void testGetUserProfile_NotLoggedIn() throws Exception {
//        mockMvc.perform(get("/account"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/"));
//    }
//}
