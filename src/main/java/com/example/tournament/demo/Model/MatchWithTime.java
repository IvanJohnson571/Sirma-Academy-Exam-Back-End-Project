package com.example.tournament.demo.Model;

public class MatchWithTime {
    public Match getMatch() {
        return match;
    }

    public int getTimePlayedTogether() {
        return timePlayedTogether;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public void setTimePlayedTogether(int timePlayedTogether) {
        this.timePlayedTogether = timePlayedTogether;
    }

    private Match match;
    private int timePlayedTogether;

    public MatchWithTime(Match match, int timePlayedTogether) {
        this.match = match;
        this.timePlayedTogether = timePlayedTogether;
    }

}