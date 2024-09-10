package com.example.tournament.demo.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Records {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long playerId;
    private Long matchId;
    private Integer fromMinutes;
    private Integer toMinutes;

    public Records(Long id, Long playerId, Long matchId, Integer fromMinutes, Integer toMinutes) {
        this.id = id;
        this.playerId = playerId;
        this.matchId = matchId;
        this.fromMinutes = fromMinutes;
        this.toMinutes = toMinutes;
    }

    public Records() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public Integer getFromMinutes() {
        return fromMinutes;
    }

    public void setFromMinutes(Integer fromMinutes) {
        this.fromMinutes = fromMinutes;
    }

    public Integer getToMinutes() {
        return toMinutes;
    }

    public void setToMinutes(Integer toMinutes) {
        this.toMinutes = toMinutes;
    }
}
