package com.example.tournament.demo.Service;
import com.example.tournament.demo.DTO.TeamDTO;
import com.example.tournament.demo.Model.*;
import com.example.tournament.demo.Repository.MatchRepository;
import com.example.tournament.demo.Repository.TeamRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TeamService {


    private final TeamRepository teamRepository;
    private final MatchRepository matchRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

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

    public List<GroupStageResponse> getGroupStageTeams() {
        LocalDate groupStageEndDate = LocalDate.of(2024, 6, 26);
        List<String> groupsNames = Arrays.asList("A", "B", "C", "D", "E", "F");

        List<Team> allTeams = teamRepository.findAll();

        Map<String, List<Team>> teamsByGroup = allTeams.stream()
                .filter(team -> groupsNames.contains(team.getGroupName()))
                .collect(Collectors.groupingBy(Team::getGroupName));

        List<GroupStageResponse> groupStageResponses = new ArrayList<>();
        for (String groupName : groupsNames) {

            List<Team> teamsInGroup = teamsByGroup.getOrDefault(groupName, Collections.emptyList());

            List<TeamResponse> teamsResponse = teamsInGroup.stream()
                    .map(team -> new TeamResponse(team.getId(), team.getName()))
                    .collect(Collectors.toList());

            groupStageResponses.add(new GroupStageResponse(groupName, teamsResponse));
        }

        return groupStageResponses;
    }

    public GroupDetailsResponse getGroupDetails(List<TeamPoints> teamsInGroup) {
        // Вземаме само мачовете от първата фаза (първите 36 мача)
        List<Match> matchesInGroup = matchRepository.findAll().stream()
                .filter(match -> match.getId() <= 36) // Филтрираме мачовете до 36-ти
                .filter(match -> isMatchInGroup(match, teamsInGroup)) // Проверяваме дали мачът е между отбори в групата
                .collect(Collectors.toList());

        List<MatchResponse> finalMatchesFromTheGroup = new ArrayList<>();
        for (Match match : matchesInGroup) {
            String result = match.getScore().split("\r")[0];
            int aTeamScore = Integer.parseInt(result.split("-")[0]);
            int bTeamScore = Integer.parseInt(result.split("-")[1]);

            Long winnerId = null;
            String winnerName = null;

            if (aTeamScore > bTeamScore) {
                winnerId = match.getATeamId();
                winnerName = getTeamNameById(teamsInGroup, winnerId);
            } else if (bTeamScore > aTeamScore) {
                winnerId = match.getBTeamId();
                winnerName = getTeamNameById(teamsInGroup, winnerId);
            }

            finalMatchesFromTheGroup.add(new MatchResponse(
                    match.getId(), match.getATeamId(), match.getBTeamId(),
                    getTeamNameById(teamsInGroup, match.getATeamId()),
                    getTeamNameById(teamsInGroup, match.getBTeamId()),
                    match.getDate(), result, winnerId, winnerName));

            // Обновяваме точките и головия баланс за отборите
            updateTeamPoints(teamsInGroup, match.getATeamId(), aTeamScore, bTeamScore, aTeamScore > bTeamScore);
            updateTeamPoints(teamsInGroup, match.getBTeamId(), bTeamScore, aTeamScore, bTeamScore > aTeamScore);
        }

        // Сортиране по точки и голов баланс
        List<TeamPoints> sortedTeams = teamsInGroup.stream()
                .sorted(Comparator.comparingInt(TeamPoints::getPoints)
                        .thenComparingInt(TeamPoints::getBalance).reversed())
                .collect(Collectors.toList());

        // Взимаме топ 2 отбора
        List<TeamPoints> topTwoTeams = sortedTeams.stream().limit(2).collect(Collectors.toList());

        return new GroupDetailsResponse(topTwoTeams, finalMatchesFromTheGroup);
    }

    private boolean isMatchInGroup(Match match, List<TeamPoints> teamsInGroup) {
        return teamsInGroup.stream()
                .anyMatch(team -> team.getId().equals(match.getATeamId()) || team.getId().equals(match.getBTeamId()));
    }

    private String getTeamNameById(List<TeamPoints> teamsInGroup, Long teamId) {
        return teamsInGroup.stream()
                .filter(team -> team.getId().equals(teamId))
                .map(TeamPoints::getName)
                .findFirst()
                .orElse("Unknown Team");
    }

    private void updateTeamPoints(List<TeamPoints> teamPointsList, Long teamId, int goalsPlus, int goalsOut, boolean isWinner) {
        for (TeamPoints teamPoints : teamPointsList) {
            if (teamPoints.getId().equals(teamId)) {
                teamPoints.setGoalsPlus(teamPoints.getGoalsPlus() + goalsPlus);
                teamPoints.setGoalsOut(teamPoints.getGoalsOut() + goalsOut);
                if (isWinner) {
                    teamPoints.setPoints(teamPoints.getPoints() + 3);
                } else if (goalsPlus == goalsOut) {
                    teamPoints.setPoints(teamPoints.getPoints() + 1);
                }
                teamPoints.setBalance(teamPoints.getGoalsPlus() - teamPoints.getGoalsOut());
                break;
            }
        }
    }

    public TeamDTO getTeamById(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Team not found for id: " + id));
        return convertToDTO(team);
    }

    public TeamDTO createTeam(@Valid TeamDTO teamDTO) {
        Team team = convertToEntity(teamDTO);
        teamRepository.save(team);
        return convertToDTO(team);
    }

    public TeamDTO updateTeam(Long id, @Valid TeamDTO teamDTO) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Team not found for id: " + id));
        team.setName(teamDTO.getName());
        team.setManagerFullName(teamDTO.getManagerFullName());
        team.setGroupName(teamDTO.getGroupName());
        teamRepository.save(team);
        return convertToDTO(team);
    }

    public void deleteTeam(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Team not found for id: " + id));
        teamRepository.delete(team);
    }

    private Team convertToEntity(TeamDTO teamDTO) {
        Team team = new Team();
        team.setId(teamDTO.getId());
        team.setName(teamDTO.getName());
        team.setManagerFullName(teamDTO.getManagerFullName());
        team.setGroupName(teamDTO.getGroupName());
        return team;
    }

}