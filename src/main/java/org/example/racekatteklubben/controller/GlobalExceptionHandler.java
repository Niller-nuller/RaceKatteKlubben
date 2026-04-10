package org.example.racekatteklubben.controller;

import org.example.racekatteklubben.exception.EmailValidationException;
import org.example.racekatteklubben.exception.PasswordValidationException;
import org.example.racekatteklubben.exception.ValidatorException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidatorException.class)
    public String handleException(ValidatorException message, Model model) {
        model.addAttribute("error", message.getMessage());
        return "error";
    }
    @ExceptionHandler
    public String handleException(IllegalArgumentException message, Model model) {
        model.addAttribute("error", message.getMessage());
        return "error";
    }
}
