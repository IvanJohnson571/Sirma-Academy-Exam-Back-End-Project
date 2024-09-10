package com.example.tournament.demo.Model;

public class PlayerWithMinutes {
    private Player player;
    private int minutesPlayed;

    public PlayerWithMinutes(Player player, int minutesPlayed) {
        this.player = player;
        this.minutesPlayed = minutesPlayed;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getMinutesPlayed() {
        return minutesPlayed;
    }

    public void setMinutesPlayed(int minutesPlayed) {
        this.minutesPlayed = minutesPlayed;
    }
}

