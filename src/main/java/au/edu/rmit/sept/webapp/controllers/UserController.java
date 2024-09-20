package au.edu.rmit.sept.webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import au.edu.rmit.sept.webapp.models.User;
import au.edu.rmit.sept.webapp.services.UserService;

import java.text.AttributedString;


@Controller
public class UserController {
    private final UserService userS;

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
    public String login(@RequestParam String email, @RequestParam String password, Model model){
        if(userS.verifyUser(email, password)){
            User user = userS.findByEmail(email);
            model.addAttribute("user", user);
            return "profile";
        }
        return "redirect:/login";
    }


}
