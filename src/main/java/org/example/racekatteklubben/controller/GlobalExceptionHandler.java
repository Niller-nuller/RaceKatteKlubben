package org.example.racekatteklubben.controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.util.zip.DataFormatException;

@ControllerAdvice
public class GlobalExceptionHandler {

    //Her skal der implementers "Custom" Exceptions.


    //Under her skal "generic" Exceptions leve.
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleException(IllegalArgumentException message, Model model) {
        model.addAttribute("error", message.getMessage());
        return "error";
    }
    @ExceptionHandler(DataFormatException.class)
    public String handleException(DataFormatException message, Model model) {
        model.addAttribute("error", message.getMessage());
        return "error";
    }
    @ExceptionHandler(NullPointerException.class)
    public String handleException(NullPointerException message, Model model) {
        model.addAttribute("error", message.getMessage());
        return "error";
    }
    @ExceptionHandler(SQLException.class)
    public String handleException(SQLException message, Model model) {
        model.addAttribute("error", message.getMessage());
        return "error";
    }
    @ExceptionHandler(Exception.class)
    public String handleException(Exception message, Model model) {
        model.addAttribute("error", message.getMessage());
        return "error";
    }
}
