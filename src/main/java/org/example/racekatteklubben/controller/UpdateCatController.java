package org.example.racekatteklubben.controller;

import jakarta.servlet.http.HttpSession;
import org.example.racekatteklubben.entity.Cat;
import org.example.racekatteklubben.entity.Gender;
import org.example.racekatteklubben.entity.User;
import org.example.racekatteklubben.usecase.CatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cats")
public class UpdateCatController {

    private final CatService catService;

    public UpdateCatController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable long id, HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            return "redirect:/login";
        }

        Cat cat = catService.getCatById(id);
        if (cat == null || cat.getOwnerId() != user.getId()) {
            return "redirect:/myprofile";
        }

        model.addAttribute("cat", cat);
        model.addAttribute("genders", Gender.values());
        return "editCat";
    }

    @PostMapping("/edit/{id}")
    public String updateCat(@PathVariable long id, @ModelAttribute Cat cat,
                            HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            return "redirect:/login";
        }

        cat.setId(id);
        cat.setOwnerId(user.getId());

        if (cat.getName() == null || cat.getName().isBlank()
                || cat.getDateOfBirth() == null || cat.getGender() == null) {
            model.addAttribute("error", "Navn, fødselsdato og køn er påkrævet");
            model.addAttribute("genders", Gender.values());
            return "editCat";
        }

        catService.updateCat(cat);
        return "redirect:/myprofile";
    }
}
