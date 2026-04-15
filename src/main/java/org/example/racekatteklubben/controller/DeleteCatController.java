package org.example.racekatteklubben.controller;

import jakarta.servlet.http.HttpSession;
import org.example.racekatteklubben.entity.User;
import org.example.racekatteklubben.use_case.CatService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cats")
public class DeleteCatController {

    private final CatService catService;

    public DeleteCatController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping("/annihilate/{id}")
    public String annihilate(@PathVariable long id, HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            return "redirect:/login";
        }

        catService.annihilateCat(id, user.getId());
        return "redirect:/myprofile";

    }
}
