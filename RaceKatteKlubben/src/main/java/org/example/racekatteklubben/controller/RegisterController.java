package org.example.racekatteklubben.controller;


import org.example.racekatteklubben.entity.UserLogin;
import org.example.racekatteklubben.use_case.RegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new UserLogin());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") UserLogin userLogin){
        registerService.register(userLogin);
        return "success";
    }
}
