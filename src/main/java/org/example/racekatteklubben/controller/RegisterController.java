package org.example.racekatteklubben.controller;


import org.example.racekatteklubben.entity.UserLogin;
import org.example.racekatteklubben.use_case.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    private final RegisterService registerService;
    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("UserLogin", new UserLogin());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("UserLogin") UserLogin userLogin){
        registerService.register(userLogin);
        return "success";// go to user information page
    }
}
