package com.example.tournament.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PlayerDTO {

    private Long id;
    @Positive(message = "Team number must be positive")
    private int teamNumber;
    @NotBlank(message = "Position cannot be blank")
    private String position;
    @NotBlank(message = "Full name cannot be blank")
    private String fullName;
    @NotNull(message = "Team ID cannot be null")
    private Long teamId;

    public PlayerDTO() {}

    public PlayerDTO(Long id, int teamNumber, String position, String fullName, Long teamId) {
        this.id = id;
        this.teamNumber = teamNumber;
        this.position = position;
        this.fullName = fullName;
        this.teamId = teamId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(int teamNumber) {
        this.teamNumber = teamNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}
