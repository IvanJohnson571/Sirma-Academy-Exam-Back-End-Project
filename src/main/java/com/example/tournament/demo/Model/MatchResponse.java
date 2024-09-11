package com.example.tournament.demo.Model;

public class MatchResponse {
    public Long getId() {
        return id;
    }

    public Long getaTeamId() {
        return aTeamId;
    }

    public Long getbTeamId() {
        return bTeamId;
    }

    public String getaTeamName() {
        return aTeamName;
    }

    public String getbTeamName() {
        return bTeamName;
    }

    public String getDate() {
        return date;
    }

    public String getResult() {
        return result;
    }

    public Long getWinnerId() {
        return winnerId;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setaTeamId(Long aTeamId) {
        this.aTeamId = aTeamId;
    }

    public void setbTeamId(Long bTeamId) {
        this.bTeamId = bTeamId;
    }

    public void setaTeamName(String aTeamName) {
        this.aTeamName = aTeamName;
    }

    public void setbTeamName(String bTeamName) {
        this.bTeamName = bTeamName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setWinnerId(Long winnerId) {
        this.winnerId = winnerId;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }

    private Long id;
    private Long aTeamId;
    private Long bTeamId;
    private String aTeamName;
    private String bTeamName;
    private String date;
    private String result;
    private Long winnerId;
    private String winnerName;

    public MatchResponse(Long id, Long aTeamId, Long bTeamId, String aTeamName, String bTeamName, String date, String result, Long winnerId, String winnerName) {
        this.id = id;
        this.aTeamId = aTeamId;
        this.bTeamId = bTeamId;
        this.aTeamName = aTeamName;
        this.bTeamName = bTeamName;
        this.date = date;
        this.result = result;
        this.winnerId = winnerId;
        this.winnerName = winnerName;
    }

}


