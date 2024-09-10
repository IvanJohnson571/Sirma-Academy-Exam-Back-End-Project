package com.example.tournament.demo.Controller;
import com.example.tournament.demo.DTO.PlayerDTO;
import com.example.tournament.demo.Model.*;
import com.example.tournament.demo.Service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/players")
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<PlayerDTO> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping("/teams/{teamAId}/{teamBId}")
    public Map<String, List<Player>> getPlayersByTwoTeams(@PathVariable Long teamAId, @PathVariable Long teamBId) {
        return playerService.getPlayersByTwoTeams(teamAId, teamBId);
    }

    @GetMapping("/played-together-longest")
    public PlayerPairWithMatches getPlayersPlayedTogetherLongest() {
        return playerService.findPlayersPlayedTogetherLongest();
    }

    @GetMapping("/minutes-played")
    public List<PlayerWithMinutes> getPlayersByMinutesPlayed() {
        return playerService.getPlayersByMinutesPlayed();
    }

    @GetMapping("/player-duration/{playerId}")
    public PlayerDurationResponse getPlayerDuration(@PathVariable Long playerId) {
        return playerService.getPlayerDuration(playerId);
    }

    @GetMapping("/player-duration-all")
    public List<PlayerDurationResponse> getAllPlayerDurations() {
        return playerService.getAllPlayerDurations();
    }

    @GetMapping("/top10-player-duration")
    public List<PlayerDurationResponse> getTop10PlayerDurations() {
        return playerService.getTop10PlayerDurations();
    }

    @GetMapping("/players-played-together")
    public List<PlayerPair> getPlayersPlayedTogether() {
        return playerService.findPlayersWhoPlayedTogether();
    }

}