package org.example.racekatteklubben.controller;

import jakarta.servlet.http.HttpSession;
import org.example.racekatteklubben.entity.Cat;
import org.example.racekatteklubben.entity.Gender;
import org.example.racekatteklubben.use_case.CatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cats")
public class CreateCatController {

    private final CatService catService;

    public CreateCatController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("cat", new Cat());
        model.addAttribute("genders", Gender.values());
        return "createCat";
    }

    @PostMapping("/create")
    public String createCat(@ModelAttribute Cat cat, HttpSession session, Model model) {

        Long ownerId = (Long) session.getAttribute("userId"); //check id til senere;
        if (ownerId == null) {
            // ERROR PAGE
        }

        // Simpel validering af påkrævede felter
        if (cat.getName() == null || cat.getName().isBlank()
                || cat.getDateOfBirth() == null || cat.getGender() == null) {
            model.addAttribute("error", "Navn, fødselsdato og køn er påkrævet");
            model.addAttribute("genders", Gender.values());
            return "createCat";
        }

        catService.createCat(cat, ownerId);
        return "redirect:/myprofile/{ownerId}";
    }
}
