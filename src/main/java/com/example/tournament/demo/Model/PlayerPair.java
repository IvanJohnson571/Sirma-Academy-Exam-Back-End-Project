package com.example.tournament.demo.Model;

public class PlayerPair {
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

    private Player playerA;
    private Player playerB;

    public PlayerPair(Player playerA, Player playerB) {
        this.playerA = playerA;
        this.playerB = playerB;
    }

}

