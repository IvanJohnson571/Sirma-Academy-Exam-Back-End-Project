package com.example.tournament.demo.Service;
import com.example.tournament.demo.DTO.TeamDTO;
import com.example.tournament.demo.Model.Team;
import com.example.tournament.demo.Repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public List<TeamDTO> getAllTeams() {
        List<Team> teams = teamRepository.findAll();
        return teams.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private TeamDTO convertToDTO(Team team) {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setId(team.getId());
        teamDTO.setName(team.getName());
        teamDTO.setManagerFullName(team.getManagerFullName());
        teamDTO.setGroupName(team.getGroupName());
        return teamDTO;
    }

}