package com.example.tournament.demo.Controller;
import com.example.tournament.demo.DTO.PlayerDTO;
import com.example.tournament.demo.Model.Player;
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

    // Нов метод за връщане на играчи от два отбора
    @GetMapping("/teams/{teamAId}/{teamBId}")
    public Map<String, List<Player>> getPlayersByTwoTeams(@PathVariable Long teamAId, @PathVariable Long teamBId) {
        return playerService.getPlayersByTwoTeams(teamAId, teamBId);
    }

}
