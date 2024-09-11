package com.example.tournament.demo.Controller;
import com.example.tournament.demo.DTO.TeamDTO;
import com.example.tournament.demo.Model.GroupDetailsResponse;
import com.example.tournament.demo.Model.GroupStageResponse;
import com.example.tournament.demo.Model.TeamPoints;
import com.example.tournament.demo.Service.TeamService;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping("")
    public List<TeamDTO> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/group-stage-teams")
    public List<GroupStageResponse> getGroupStageTeams() {
        return teamService.getGroupStageTeams();
    }

    @PostMapping("/group-details")
    public ResponseEntity<GroupDetailsResponse> getGroupDetails(@RequestBody List<TeamPoints> teamsInGroup) {
        GroupDetailsResponse response = teamService.getGroupDetails(teamsInGroup);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}