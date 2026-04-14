package org.example.racekatteklubben.controller;

import org.example.racekatteklubben.use_case.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cat")
public class ReadCatController {

    private CatService catService;

    @Autowired
    public void setCatService(CatService catService) {
        this.catService = catService;
    }

    @GetMapping("/cat-list")
    public String catList(Model model,String criteria){
        model.addAttribute("searchCriteria", catService.handleGetFullListOfCats(criteria));
        return "cat-list";
    }
}
