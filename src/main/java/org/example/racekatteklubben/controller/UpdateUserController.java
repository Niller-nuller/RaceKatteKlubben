package org.example.racekatteklubben.controller;

import jakarta.servlet.http.HttpSession;
import org.example.racekatteklubben.entity.AuthObject;
import org.example.racekatteklubben.entity.Gender;
import org.example.racekatteklubben.entity.User;
import org.example.racekatteklubben.usecase.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UpdateUserController {

    private final UserService userService;

    @Autowired
    public UpdateUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/edit")
    public String showEditForm(HttpSession session, Model model) {
        AuthObject authUser = (AuthObject) session.getAttribute("AuthUser");
        if (authUser == null) {
            return "redirect:/login";
        }

        User user = userService.getUserFromAuth(authUser);
        model.addAttribute("editUser", user);
        model.addAttribute("genders", Gender.values());
        return "editUser";
    }

    @PostMapping("/edit")
    public String updateUser(@ModelAttribute("editUser") User editUser, HttpSession session, Model model) {
        AuthObject authUser = (AuthObject) session.getAttribute("AuthUser");
        if (authUser == null) {
            return "redirect:/login";
        }

        if (editUser.getName() == null || editUser.getName().isBlank()
                || editUser.getLastName() == null || editUser.getLastName().isBlank()
                || editUser.getGender() == null) {
            model.addAttribute("error", "Fornavn, efternavn og køn er påkrævet");
            model.addAttribute("genders", Gender.values());
            return "editUser";
        }

        userService.updateUser(editUser, authUser);
        return "redirect:/myprofile";
    }
}
