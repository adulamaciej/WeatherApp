package org.example.weatherapplicaton.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(CityNotFoundException.class)
    public String handleCityNotFoundException(Model model) {
        model.addAttribute("errorMessage", "City not found. Please check the spelling and try again.");
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneralException(Model model) {
        model.addAttribute("errorMessage", "An unexpected error occurred. Please try again later.");
        return "error";
    }
}