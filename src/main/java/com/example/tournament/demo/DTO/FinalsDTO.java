package com.example.tournament.demo.DTO;

public class FinalsDTO {

    private Long id;
    private Long aTeamId;
    private String aTeamName;
    private Long bTeamId;
    private String bTeamName;
    private String date;
    private String score;
    private String winnerName; // Placeholder за по-късна логика

    public FinalsDTO(Long id, Long aTeamId, String aTeamName, Long bTeamId, String bTeamName, String date, String score, String winnerName) {
        this.id = id;
        this.aTeamId = aTeamId;
        this.aTeamName = aTeamName;
        this.bTeamId = bTeamId;
        this.bTeamName = bTeamName;
        this.date = date;
        this.score = score;
        this.winnerName = winnerName;
    }

    // Getters и Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getATeamId() {
        return aTeamId;
    }

    public void setATeamId(Long aTeamId) {
        this.aTeamId = aTeamId;
    }

    public String getATeamName() {
        return aTeamName;
    }

    public void setATeamName(String aTeamName) {
        this.aTeamName = aTeamName;
    }

    public Long getBTeamId() {
        return bTeamId;
    }

    public void setBTeamId(Long bTeamId) {
        this.bTeamId = bTeamId;
    }

    public String getBTeamName() {
        return bTeamName;
    }

    public void setBTeamName(String bTeamName) {
        this.bTeamName = bTeamName;
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

    public String getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }
}
