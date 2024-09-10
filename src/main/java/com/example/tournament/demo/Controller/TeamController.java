package com.example.tournament.demo.Controller;
import com.example.tournament.demo.DTO.TeamDTO;
import com.example.tournament.demo.Service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@Validated
@CrossOrigin(origins = "http://localhost:5173")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping("/teams")
    public List<TeamDTO> getAllTeams() {
        return teamService.getAllTeams();
    }

}