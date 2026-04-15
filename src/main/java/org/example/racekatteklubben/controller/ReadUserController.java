package org.example.racekatteklubben.controller;


import org.example.racekatteklubben.use_case.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class ReadUserController {

   private final UserService userService;

   @Autowired
   public ReadUserController(UserService userService){
       this.userService = userService;
   }

   @GetMapping("/user-list")
    public String handleGetAllUsers(Model model,@RequestParam(value = "criteria", required = false, defaultValue = "") String criteria){
       model.addAttribute("searchCriteria", criteria);
       model.addAttribute("userList", userService.handleGetAllUsers(criteria));
       return "user-list";
   }
}
