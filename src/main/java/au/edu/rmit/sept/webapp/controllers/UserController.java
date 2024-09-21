package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.models.User;
import au.edu.rmit.sept.webapp.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {
    private final UserService userS;
//    private Map<String, Object> response = new HashMap<>();
    @Autowired
    public UserController(UserService userS){
        this.userS = userS;
    }


    // ModelAttribute is going to get the inputs of the form and turn it to a User object
    @PostMapping("/signup")
    public String createUser(@ModelAttribute User user){
        userS.createUser(user);
        return "redirect:/";
    }


    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        if (userS.verifyUser(email, password)) {
            session.setAttribute("loggedInUser", email);
            System.out.println("Logged in user: " + email);
            return "redirect:/profile";
        } else {
            model.addAttribute("errorMessage", "Invalid email or password");
            return "login";
        }
    }


    @GetMapping("/profile")
    public String getUserProfile(HttpSession session, Model model) {

        String email = (String) session.getAttribute("loggedInUser");
        if (email != null) {

            User user = userS.findByEmail(email);
            if (user != null) {
                model.addAttribute("user", user);
                return "Profile";
            } else {
                model.addAttribute("errorMessage", "User not found.");
                return "ErrorPage";
            }
        } else {

            return "redirect:/login";
        }
    }

    //httpSession thingy reference: https://docs.oracle.com/javaee/7/api/javax/servlet/http/HttpSession.html

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

}




























//package au.edu.rmit.sept.webapp.controllers;
//
//import au.edu.rmit.sept.webapp.models.User;
//import au.edu.rmit.sept.webapp.services.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//public class UserController {
//
//    private final UserService userService;
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @PostMapping("/signup")
//    public String createUser(@ModelAttribute User user, Model model) {
//        userService.createUser(user);
//        return "redirect:/login";
//    }
//
//    @PostMapping("/login")
//    public String login(@RequestParam String email, @RequestParam String password, Model model) {
//        boolean loggedInUser = userService.verifyUser(email, password);
//        if (loggedInUser == true) {
//            model.addAttribute("user", true);
//            return "profile";
//        } else {
//            model.addAttribute("errorMessage", "Invalid email or password");
//            return "login";
//        }
//    }
//
//    @PostMapping("/logout")
//    public String logout(Model model) {
//        model.addAttribute("user", null);
//    }
//}
























//    @PostMapping("/login")
//    public String login(@RequestParam String email, @RequestParam String password, Model model){
//        if(userS.verifyUser(email, password)){
//            User user = userS.findByEmail(email);
//            model.addAttribute("user", user);
//            return "profile";
//        }
//        return "redirect:/login";
//    }



