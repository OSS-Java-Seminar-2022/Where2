package com.falsefalse.where2.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgument(IllegalArgumentException ex) {
        System.out.println(HttpStatus.BAD_REQUEST + ": " + ex.getMessage());
        return "redirect:/events?exception";
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElement(NoSuchElementException ex) {
        System.out.println(HttpStatus.NOT_FOUND + ": " + ex.getMessage());
        return "redirect:/events?exception";
    }
}
