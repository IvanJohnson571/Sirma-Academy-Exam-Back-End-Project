package com.example.tournament.demo.Controller;
import com.example.tournament.demo.DTO.PlayerDTO;
import com.example.tournament.demo.Service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

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
}
