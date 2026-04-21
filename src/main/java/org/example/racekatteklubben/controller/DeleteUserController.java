package org.example.racekatteklubben.controller;

import jakarta.servlet.http.HttpSession;
import org.example.racekatteklubben.entity.User;
import org.example.racekatteklubben.usecase.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class DeleteUserController {

    private final UserService userService;

    public DeleteUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable long id, HttpSession session) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/login";
        }
        userService.handleDeleteUser(user);
        return "redirect:/logout";
    }


}
