package org.example.racekatteklubben.controller;
import org.example.racekatteklubben.exception.ValidatorException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.zip.DataFormatException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public String handleException(RuntimeException message, Model model) {
        model.addAttribute("error", message.getMessage());
        return "error";
    }
    @ExceptionHandler
    public String handleException(IllegalArgumentException message, Model model) {
        model.addAttribute("error", message.getMessage());
        return "error";
    }
    @ExceptionHandler
    public String handleException(DataFormatException message, Model model) {
        model.addAttribute("error", message.getMessage());
        return "error";
    }
    @ExceptionHandler
    public String handleException(ValidatorException message, Model model) {
        model.addAttribute("error", message.getMessage());
        return "error";
    }
}
