package org.example.racekatteklubben.controller;

import jakarta.servlet.http.HttpSession;
import org.example.racekatteklubben.exception.EmailValidationException;
import org.example.racekatteklubben.exception.PasswordValidationException;
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
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("email") String email,@ModelAttribute("password") String password, HttpSession session,Model model) {
        try {
            session.setAttribute("currentUser", logInService.login(email, password));
        }catch (EmailValidationException | PasswordValidationException ex){
            model.addAttribute("errorMessage", ex.getMessage());
            return "login";
        }
        return "redirect:/";
    }
}
