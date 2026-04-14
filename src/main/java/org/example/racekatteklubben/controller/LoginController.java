package org.example.racekatteklubben.controller;

import jakarta.servlet.http.HttpSession;
import org.example.racekatteklubben.entity.Auth;
import org.example.racekatteklubben.use_case.LogInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final LogInService logInService;
    @Autowired
    public LoginController(LogInService logInService) {
        this.logInService = logInService;
    }

    @GetMapping("/login")
    public String login(@ModelAttribute Auth auth) {
        return "login";
    }

    @PostMapping("/login")
    public String login(Model model, @RequestParam String email, @RequestParam String password, HttpSession session) {
        Auth auth = logInService.login(new Auth(email, password));

        if (auth == null) {
            model.addAttribute("error", "Ingen bruger fundet med den email");
            return "/login";
        }
        session.setAttribute("loggedInUser", auth);
        System.out.println("User er: " + auth.getEmail() + ", " + auth.getPassword());
        return "redirect:/";
    }
}
