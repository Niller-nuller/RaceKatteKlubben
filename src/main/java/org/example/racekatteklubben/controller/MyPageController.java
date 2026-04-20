package org.example.racekatteklubben.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.example.racekatteklubben.entity.User;
import org.example.racekatteklubben.use_case.CatService;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class MyPageController {

    private final CatService catService;

    public MyPageController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping("/myprofile")
    public String profile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            return "redirect:/login";
        }
        long userId = user.getId();
        System.out.println(user.getId());
        System.out.println(user.getUsersLogin().getEmail());


        model.addAttribute("authUser", ((User) session.getAttribute("currentUser")).getUsersLogin());
        model.addAttribute("user", user);
        model.addAttribute("cats", catService.listCatById(userId));
        return "/myprofile";
    }
}


