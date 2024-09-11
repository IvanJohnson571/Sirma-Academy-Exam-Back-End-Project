package com.example.tournament.demo.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Match {
    public Long getId() {
        return id;
    }

    public Long getaTeamId() {
        return aTeamId;
    }

    public Long getbTeamId() {
        return bTeamId;
    }

    public String getDate() {
        return date;
    }

    public String getScore() {
        return score;
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

    public void setDate(String date) {
        this.date = date;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long aTeamId;
    private Long bTeamId;
    private String date;
    private String score;

    public Match() {}

    public Match(Long id, Long aTeamId, Long bTeamId, String date, String score) {
        this.id = id;
        this.aTeamId = aTeamId;
        this.bTeamId = bTeamId;
        this.date = date;
        this.score = score;
    }

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

}
