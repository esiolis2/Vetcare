package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.models.User;
import au.edu.rmit.sept.webapp.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;


@Controller
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/signup")
    public String createUser(@ModelAttribute User user){
        userService.createUser(user);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestParam String email, @RequestParam String password, HttpServletRequest request) {
        boolean isValidUser = userService.verifyUser(email, password);
        Map<String, Object> response = new HashMap<>();
//        if (isValidUser) {
//            request.getSession().setAttribute("userEmail", email);

            if (isValidUser) {
                User user = userService.findByEmail(email);
                request.getSession().setAttribute("userId", user.getId());
                request.getSession().setAttribute("userEmail", email);

                response.put("success", true);
            response.put("message", "Login successful");
        } else {
            response.put("success", false);
            response.put("message", "Invalid email or password");
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/account")
    public String getUserProfile(Model model, HttpServletRequest request) {
        String email = (String) request.getSession().getAttribute("userEmail"); // Or however you're storing it
        if (email != null) {
            User user = userService.findByEmail(email);
            System.out.println("the email is: " + email );
            model.addAttribute("user", user);
            return "account"; // Make sure this matches your profile HTML file name
        }
        return "redirect:/"; // Redirect to home if not logged in
    }
}




