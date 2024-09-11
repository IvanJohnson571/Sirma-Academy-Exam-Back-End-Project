package com.example.tournament.demo.Exceptions;

import com.example.tournament.demo.Exceptions.Dates.InvalidDateFormatException;
import com.example.tournament.demo.Exceptions.Match.MatchNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MatchNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleMatchNotFoundException(MatchNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(InvalidDateFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidDateFormatException(InvalidDateFormatException ex) {
        return ex.getMessage();
    }
}
