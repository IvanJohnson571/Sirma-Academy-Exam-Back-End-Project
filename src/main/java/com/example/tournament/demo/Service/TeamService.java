package com.example.tournament.demo.Service;

import com.example.tournament.demo.DTO.TeamDTO;
import com.example.tournament.demo.Model.Team.Team;
import com.example.tournament.demo.Repository.TeamRepository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public List<TeamDTO> getAllTeams() {
        // Извличане на всички отбори и конвертиране в DTO
        List<Team> teams = teamRepository.findAll();
        return teams.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Метод за конвертиране на Team в TeamDTO
    private TeamDTO convertToDTO(Team team) {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setId(team.getId());
        teamDTO.setName(team.getName());
        teamDTO.setManagerFullName(team.getManagerFullName());
        teamDTO.setGroupName(team.getGroupName());
        return teamDTO;
    }

}
