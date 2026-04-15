package org.example.racekatteklubben.controller;

import jakarta.servlet.http.HttpSession;
import org.example.racekatteklubben.entity.Auth;
import org.example.racekatteklubben.entity.User;
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
    public String login(Model model) {
        model.addAttribute("Auth", new Auth());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("Auth") Auth auth, HttpSession session, Model model) {

        session.setAttribute("AuthUser", logInService.login(auth));

        return "redirect:/";
    }
}
