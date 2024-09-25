
package au.edu.rmit.sept.webapp.controllers;

import au.edu.rmit.sept.webapp.models.PetInformation;
import au.edu.rmit.sept.webapp.services.PetInformationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import au.edu.rmit.sept.webapp.models.User;
import au.edu.rmit.sept.webapp.services.UserService;


@Controller
@SessionAttributes("loggedInUser")
public class UserController {
    private final UserService userService;
    private final PetInformationService petInfoService;
    @Autowired
    public UserController(UserService userService, PetInformationService petInfoService){
        this.userService = userService;
        this.petInfoService = petInfoService;
    }



    @PostMapping("/signup")
    public String createUser(@ModelAttribute User user){
        userService.createUser(user);
        return "redirect:/login";
    }


    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model, HttpServletRequest request) {
        boolean isValidUser = userService.verifyUser(email, password);
        if (isValidUser) {
            User user = userService.findByEmail(email);
            // storing user info in the session to maintain login state
            request.getSession().setAttribute("loggedInUser", user);
            request.getSession().setAttribute("userEmail", email);

            // added logged-in user to the model to pass data to the view
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
    public String logout(HttpServletRequest request,Model model) {
//        terminating the session
        request.getSession().invalidate();
        System.out.println("Logged out!!!");
//        for proper navbar after logging out
        model.addAttribute("loggedInUser", null);
        return "redirect:/";
    }


    @PostMapping("/account/pet-register")
    public String registerPet(@ModelAttribute PetInformation petInformation, HttpServletRequest request) {
        User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
        if (loggedInUser != null) {
            petInformation.setOwnerName(loggedInUser.getName());
//            petInformation.setOwnerContact(loggedInUser.getEmail());
            petInfoService.createPetInformation(petInformation);
            System.out.println("Pet registered successfully!!!!");
        }
        return "redirect:/";

    }


}

