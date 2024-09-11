package com.example.tournament.demo.Exceptions.Match;

public class MatchNotFoundException extends RuntimeException {
    public MatchNotFoundException(Long id) {
        super("Match with ID " + id + " not found.");
    }
}
