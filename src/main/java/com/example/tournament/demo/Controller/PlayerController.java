package com.example.tournament.demo.Controller;
import com.example.tournament.demo.DTO.PlayerDTO;
import com.example.tournament.demo.Model.*;
import com.example.tournament.demo.Service.PlayerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    //The proper way to show two teams with substitutes.
    @GetMapping("/teams/detailed/{teamAId}/{teamBId}")
    public Map<String, List<Player>> getPlayersByTwoTeams(@PathVariable Long teamAId, @PathVariable Long teamBId) {
        return playerService.getPlayersByTwoTeams(teamAId, teamBId);
    }

    //The raw way to show two teams without separation on substitutes.
    @GetMapping("/teams/{teamAId}/{teamBId}")
    public Map<String, List<Player>> getPlayersByTwoTeamsRaw(@PathVariable Long teamAId, @PathVariable Long teamBId) {
        return playerService.getPlayersByTwoTeamsRaw(teamAId, teamBId);
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

    // --- CRUD SECTION ---

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTO> getPlayerById(@PathVariable Long id) {
        return ResponseEntity.ok(playerService.getPlayerById(id));
    }

    @PostMapping("")
    public ResponseEntity<PlayerDTO> createPlayer(@Valid @RequestBody PlayerDTO playerDTO) {
        return new ResponseEntity<>(playerService.createPlayer(playerDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayerDTO> updatePlayer(@PathVariable Long id, @Valid @RequestBody PlayerDTO playerDTO) {
        return ResponseEntity.ok(playerService.updatePlayer(id, playerDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
        return ResponseEntity.noContent().build();
    }

}