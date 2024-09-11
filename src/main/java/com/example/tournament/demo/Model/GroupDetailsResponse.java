package com.example.tournament.demo.Model;

import java.util.List;

public class GroupDetailsResponse {
    private List<TeamPoints> topTwoTeams;
    private List<MatchResponse> finalMatchesFromTheGroup;

    public GroupDetailsResponse(List<TeamPoints> topTwoTeams, List<MatchResponse> finalMatchesFromTheGroup) {
        this.topTwoTeams = topTwoTeams;
        this.finalMatchesFromTheGroup = finalMatchesFromTheGroup;
    }

    public List<TeamPoints> getTopTwoTeams() {
        return topTwoTeams;
    }

    public void setTopTwoTeams(List<TeamPoints> topTwoTeams) {
        this.topTwoTeams = topTwoTeams;
    }

    public List<MatchResponse> getFinalMatchesFromTheGroup() {
        return finalMatchesFromTheGroup;
    }

    public void setFinalMatchesFromTheGroup(List<MatchResponse> finalMatchesFromTheGroup) {
        this.finalMatchesFromTheGroup = finalMatchesFromTheGroup;
    }
}

