package com.example.tournament.demo.Model;

public class PlayerDurationResponse {
    private long duration;
    private Player player;

    public PlayerDurationResponse(long duration, Player player) {
        this.duration = duration;
        this.player = player;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}

