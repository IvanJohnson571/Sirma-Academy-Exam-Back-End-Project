package com.example.tournament.demo.Model;

import java.util.List;

public class PlayerPairWithMatches {
    public PlayerPair getPlayerPair() {
        return playerPair;
    }

    public List<MatchWithTime> getMatchesWithTime() {
        return matchesWithTime;
    }

    public void setPlayerPair(PlayerPair playerPair) {
        this.playerPair = playerPair;
    }

    public void setMatchesWithTime(List<MatchWithTime> matchesWithTime) {
        this.matchesWithTime = matchesWithTime;
    }

    private PlayerPair playerPair;
    private List<MatchWithTime> matchesWithTime;

    public PlayerPairWithMatches(PlayerPair playerPair, List<MatchWithTime> matchesWithTime) {
        this.playerPair = playerPair;
        this.matchesWithTime = matchesWithTime;
    }

}

