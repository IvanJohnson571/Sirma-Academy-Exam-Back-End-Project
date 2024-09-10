package com.example.tournament.demo.Model;

public class PairWithTime {
    public String getPlayerAName() {
        return playerAName;
    }

    public String getPlayerBName() {
        return playerBName;
    }

    public int getTotalTimePlayedTogether() {
        return totalTimePlayedTogether;
    }

    public void setPlayerAName(String playerAName) {
        this.playerAName = playerAName;
    }

    public void setPlayerBName(String playerBName) {
        this.playerBName = playerBName;
    }

    public void setTotalTimePlayedTogether(int totalTimePlayedTogether) {
        this.totalTimePlayedTogether = totalTimePlayedTogether;
    }

    private String playerAName;
    private String playerBName;
    private int totalTimePlayedTogether;

    public PairWithTime(String playerAName, String playerBName, int totalTimePlayedTogether) {
        this.playerAName = playerAName;
        this.playerBName = playerBName;
        this.totalTimePlayedTogether = totalTimePlayedTogether;
    }

}

