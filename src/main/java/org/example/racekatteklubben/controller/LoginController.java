package org.example.racekatteklubben.controller;

import jakarta.servlet.http.HttpSession;
import org.example.racekatteklubben.entity.Auth;
import org.example.racekatteklubben.use_case.LogInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final LogInService logInService;
    @Autowired
    public LoginController(LogInService logInService) {
        this.logInService = logInService;
    }

    @GetMapping("/login")
    public String login(@ModelAttribute Auth userLogin) {
        return "login";
    }

    @PostMapping("/login")
    public String login(Model model, @RequestParam String email, @RequestParam String password, HttpSession session) {
        Auth userLogin = logInService.login(new Auth(email, password));

        if (userLogin == null) {
            model.addAttribute("error", "Ingen bruger fundet med den email");
            return "/login";
        }
        session.setAttribute("AuthUser", userLogin);
        System.out.println("User er: " + userLogin.getEmail() + ", " + userLogin.getPassword());
        return "redirect:/";
    }
}
