package com.example.tournament.demo.Model;

import java.util.List;

public class PlayerPair {

    private Player playerA;
    private Player playerB;
    private List<MatchTogetherInfo> matchesTogether;

    public Player getPlayerA() {
        return playerA;
    }

    public Player getPlayerB() {
        return playerB;
    }

    public void setPlayerA(Player playerA) {
        this.playerA = playerA;
    }

    public void setPlayerB(Player playerB) {
        this.playerB = playerB;
    }

    public PlayerPair(Player playerA, Player playerB) {
        this.playerA = playerA;
        this.playerB = playerB;
    }

    public void setMatchesTogether(List<MatchTogetherInfo> matchesTogether) {
        this.matchesTogether = matchesTogether;
    }

    public List<MatchTogetherInfo> getMatchesTogether() {
        return matchesTogether;
    }

}

