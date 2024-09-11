package com.example.tournament.demo.Exceptions.Dates;

public class InvalidDateFormatException extends RuntimeException {
    public InvalidDateFormatException(String date) {
        super("Invalid date format: " + date);
    }
}
