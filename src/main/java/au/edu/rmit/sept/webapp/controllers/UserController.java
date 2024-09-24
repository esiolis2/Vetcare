//package au.edu.rmit.sept.webapp.controllers;
//
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//
//import org.springframework.web.bind.annotation.*;
//import org.springframework.ui.Model;
//
//import au.edu.rmit.sept.webapp.models.User;
//import au.edu.rmit.sept.webapp.services.UserService;
//
//import java.text.AttributedString;
//import java.util.HashMap;
//import java.util.Map;
//
//
//@Controller
//public class UserController {
//    private final UserService userService;
//    @Autowired
//    public UserController(UserService userService){
//        this.userService = userService;
//    }
//
//    @PostMapping("/signup")
//    public String createUser(@ModelAttribute User user){
//        userService.createUser(user);
//        return "redirect:/";
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<Map<String, Object>> login(@RequestParam String email, @RequestParam String password, HttpServletRequest request) {
//        boolean isValidUser = userService.verifyUser(email, password);
//        Map<String, Object> response = new HashMap<>();
//        if (isValidUser) {
//            request.getSession().setAttribute("userEmail", email);
//
//            response.put("success", true);
//            response.put("message", "Login successful");
//        } else {
//            response.put("success", false);
//            response.put("message", "Invalid email or password");
//        }
//        return ResponseEntity.ok(response);
//    }
//
//    @GetMapping("/account")
//    public String getUserProfile(Model model, HttpServletRequest request) {
//        String email = (String) request.getSession().getAttribute("userEmail"); // Or however you're storing it
//        if (email != null) {
//            User user = userService.findByEmail(email);
//            System.out.println("the email is: " + email );
//            model.addAttribute("user", user);
//            return "account"; // Make sure this matches your profile HTML file name
//        }
//        return "redirect:/"; // Redirect to home if not logged in
//    }
//}
//
//
//
//

//

package au.edu.rmit.sept.webapp.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import au.edu.rmit.sept.webapp.models.User;
import au.edu.rmit.sept.webapp.services.UserService;

import java.util.HashMap;
import java.util.Map;

@Controller
@SessionAttributes("loggedInUser")
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
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        boolean isValidUser = userService.verifyUser(email, password);
        if (isValidUser) {
            User user = userService.findByEmail(email);
            model.addAttribute("loggedInUser", user);
            System.out.println("Logged in successfully!!!!");
            return "redirect:/account";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
    }

    @GetMapping("/account")
    public String getUserProfile(Model model, @ModelAttribute("loggedInUser") User loggedInUser) {
        model.addAttribute("user", loggedInUser);
        return "account";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        System.out.println("Logged out!!!");
        return "redirect:/";
    }

}


//
//package au.edu.rmit.sept.webapp.controllers;
//
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import au.edu.rmit.sept.webapp.models.User;
//import au.edu.rmit.sept.webapp.services.UserService;
//import org.springframework.web.bind.support.SessionStatus;
//
//@Controller
//@SessionAttributes("loggedInUser")
//public class UserController {
//    private final UserService userService;
//
//    @Autowired
//    public UserController(UserService userService){
//        this.userService = userService;
//    }
//
//    @PostMapping("/signup")
//    public String createUser(@ModelAttribute User user){
//        userService.createUser(user);
//        return "redirect:/login";
//    }
//
//    @PostMapping("/login")
//    public String login(@RequestParam String email, @RequestParam String password, Model model, SessionStatus status) {
//        boolean isValidUser = userService.verifyUser(email, password);
//        if (isValidUser) {
//            User user = userService.findByEmail(email);
//            model.addAttribute("loggedInUser", user);
//            return "redirect:/account";
//        } else {
//            status.setComplete(); // Clear out session user value
//            model.addAttribute("error", "Invalid email or password");
//            return "login";
//        }
//    }
//
//    @GetMapping("/account")
//    public String getUserProfile(Model model, @ModelAttribute("loggedInUser") User loggedInUser) {
//        model.addAttribute("user", loggedInUser);
//        return "account";
//    }
//
//    @PostMapping("/logout")
//    public String logout(HttpServletRequest request, SessionStatus status) {
//        request.getSession().invalidate();
//        status.setComplete();
//        System.out.println("Logged out!");
//        return "redirect:/";
//    }
//}