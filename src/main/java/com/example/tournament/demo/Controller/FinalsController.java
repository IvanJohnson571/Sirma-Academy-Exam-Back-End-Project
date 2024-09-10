package com.example.tournament.demo.Controller;

import com.example.tournament.demo.DTO.FinalsDTO;
import com.example.tournament.demo.Model.Match;
import com.example.tournament.demo.Model.Team;
import com.example.tournament.demo.Repository.MatchRepository;
import com.example.tournament.demo.Repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/finals")
public class FinalsController {

    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public FinalsController(MatchRepository matchRepository, TeamRepository teamRepository) {
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
    }

    @GetMapping
    public Map<String, List<FinalsDTO>> getAllFinalsTeams() {
        Map<String, List<FinalsDTO>> finalsTeams = new HashMap<>();

        finalsTeams.put("SixteenFinals", buildFinalsDTOList(37L, 44L));
        finalsTeams.put("ForthFinals", buildFinalsDTOList(45L, 48L));
        finalsTeams.put("SemiFinals", buildFinalsDTOList(49L, 50L));
        finalsTeams.put("Finals", buildFinalsDTOList(51L, 51L));

        return finalsTeams;
    }

    private List<FinalsDTO> buildFinalsDTOList(Long startId, Long endId) {
        List<Match> matches = matchRepository.findByIdBetween(startId, endId);
        List<FinalsDTO> finalsDTOList = new ArrayList<>();

        for (Match match : matches) {
            Team aTeam = teamRepository.findById(match.getATeamId()).orElse(null);
            Team bTeam = teamRepository.findById(match.getBTeamId()).orElse(null);

            String aTeamName = aTeam != null ? aTeam.getName() : "Unknown";
            String bTeamName = bTeam != null ? bTeam.getName() : "Unknown";

            String score = match.getScore(); // Примерен резултат във формат "0(3)-0(0)"
            String winnerName = null;

            if (score != null && !score.isEmpty()) {
                // Проверяваме дали има скоби (дузпи)
                if (score.contains("(") && score.contains(")")) {
                    // Обработка на резултатите с дузпи, например "0(3)-0(0)"
                    String[] scoreParts = score.split("-");
                    String aTeamScore = scoreParts[0].trim();
                    String bTeamScore = scoreParts[1].trim();

                    // Извличаме числата в скобите
                    int aPenalties = extractScoreInBrackets(aTeamScore);
                    int bPenalties = extractScoreInBrackets(bTeamScore);

                    if (aPenalties > bPenalties) {
                        winnerName = aTeamName;
                    } else {
                        winnerName = bTeamName;
                    }
                } else {
                    // Стандартна обработка на резултата без дузпи
                    String[] scoreParts = score.split("-");
                    if (scoreParts.length == 2) {
                        try {
                            int aScore = Integer.parseInt(scoreParts[0].trim());
                            int bScore = Integer.parseInt(scoreParts[1].trim());

                            if (aScore > bScore) {
                                winnerName = aTeamName;
                            } else if (bScore > aScore) {
                                winnerName = bTeamName;
                            }
                        } catch (NumberFormatException e) {
                            winnerName = "Invalid score format";
                        }
                    } else {
                        winnerName = "Invalid score format";
                    }
                }
            }

            FinalsDTO finalsDTO = new FinalsDTO(
                    match.getId(),
                    match.getATeamId(),
                    aTeamName,
                    match.getBTeamId(),
                    bTeamName,
                    match.getDate(),
                    score,
                    winnerName
            );

            finalsDTOList.add(finalsDTO);
        }

        return finalsDTOList;
    }

    // Метод за извличане на резултата в скобите
    private int extractScoreInBrackets(String score) {
        String regex = "\\((\\d+)\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(score);

        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        return 0; // В случай че няма скоби или резултатът е невалиден
    }
}
