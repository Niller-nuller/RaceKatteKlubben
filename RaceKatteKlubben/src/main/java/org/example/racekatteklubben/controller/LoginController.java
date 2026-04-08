package org.example.racekatteklubben.controller;

import org.apache.catalina.User;
import org.example.racekatteklubben.entity.UserLogin;
import org.example.racekatteklubben.use_case.LogInService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    private final LogInService logInService;


    public LoginController(LogInService logInService) {
        this.logInService = logInService;
    }

}
