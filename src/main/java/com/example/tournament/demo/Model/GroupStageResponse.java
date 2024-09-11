package com.example.tournament.demo.Model;

import java.util.List;

public class GroupStageResponse {
    private String groupName;
    private List<TeamResponse> teams;

    public GroupStageResponse(String groupName, List<TeamResponse> teams) {
        this.groupName = groupName;
        this.teams = teams;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<TeamResponse> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamResponse> teams) {
        this.teams = teams;
    }
}

