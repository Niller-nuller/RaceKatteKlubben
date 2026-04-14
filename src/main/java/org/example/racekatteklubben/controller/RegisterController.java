package org.example.racekatteklubben.controller;


import org.example.racekatteklubben.entity.Gender;
import org.example.racekatteklubben.entity.RegisterWrapper;
import org.example.racekatteklubben.entity.User;
import org.example.racekatteklubben.use_case.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@Controller
public class RegisterController {

    private final RegisterService registerService;
    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("genders", Gender.values());
        model.addAttribute("registerWrapper", new RegisterWrapper());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("UserLogin") RegisterWrapper registerWrapper) {
        registerService.register(registerWrapper);
        return "index";
    }


}
