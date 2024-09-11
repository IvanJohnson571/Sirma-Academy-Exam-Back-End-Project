package com.example.tournament.demo.DTO;

public class MatchRequest {
    private Long aTeamId;
    private Long bTeamId;
    private String date;
    private String score;

    // Гетъри и сетъри
    public Long getATeamId() {
        return aTeamId;
    }

    public void setATeamId(Long aTeamId) {
        this.aTeamId = aTeamId;
    }

    public Long getBTeamId() {
        return bTeamId;
    }

    public void setBTeamId(Long bTeamId) {
        this.bTeamId = bTeamId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}


