package com.example.tournament.demo.Model;

public class MatchTogetherInfo {

    private Long matchId;
    private int togetherTime;

    public Long getMatchId() {
        return matchId;
    }

    public int getTogetherTime() {
        return togetherTime;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public void setTogetherTime(int togetherTime) {
        this.togetherTime = togetherTime;
    }

    public MatchTogetherInfo(Long matchId, int togetherTime) {
        this.matchId = matchId;
        this.togetherTime = togetherTime;
    }

}