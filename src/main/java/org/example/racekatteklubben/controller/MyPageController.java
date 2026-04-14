package org.example.racekatteklubben.controller;

import jakarta.servlet.http.HttpSession;
import org.example.racekatteklubben.entity.Auth;
import org.example.racekatteklubben.use_case.CatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyPageController {

    private final CatService catService;

    public MyPageController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping("/myprofile")
    public String profile(HttpSession session, Model model){
        Auth AuthUser = (Auth) session.getAttribute("AuthUser");
        if (AuthUser == null) {
            return "redirect:/login";
        }
        long userId = AuthUser.getId();


        model.addAttribute("AuthUser", session.getAttribute("AuthUser"));
        model.addAttribute("cats", catService.listCatById(userId));
        return "myprofile";
    }

}
