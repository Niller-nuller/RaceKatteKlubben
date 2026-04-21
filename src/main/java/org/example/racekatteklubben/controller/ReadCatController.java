package org.example.racekatteklubben.controller;

import org.example.racekatteklubben.usecase.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cat")
public class ReadCatController {

    private CatService catService;

    @Autowired
    public void setCatService(CatService catService) {
        this.catService = catService;
    }

    @GetMapping("/catList")
    public String catList(Model model,@RequestParam(value = "criteria", required = false, defaultValue = "") String criteria){
        model.addAttribute("searchCriteria", criteria);
        model.addAttribute("catList", catService.handleReturnCatList(criteria));
        return "cat-list";
    }
}
