package org.example.racekatteklubben.controller;


import org.example.racekatteklubben.entity.User;
import org.example.racekatteklubben.use_case.CreateUserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CreateUserProfileController {
    private final CreateUserProfileService createUserProfileService;
    @Autowired
    public CreateUserProfileController(CreateUserProfileService createUserProfileService) {
        this.createUserProfileService = createUserProfileService;
    }
    @GetMapping
    public String createUserProfile(Model model) {
        model.addAttribute("UserProfile", new User());
        return "createUserProfile";
    }
    @PostMapping
    public String createUserProfile(@ModelAttribute("userProfile") User user) {
        createUserProfileService.createUserProfile(user);
        return "index";
    }
}
