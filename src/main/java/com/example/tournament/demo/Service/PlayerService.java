package com.example.tournament.demo.Service;
import com.example.tournament.demo.DTO.PlayerDTO;
import com.example.tournament.demo.Model.Player;
import com.example.tournament.demo.Repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<PlayerDTO> getAllPlayers() {
        List<Player> players = playerRepository.findAll();
        return players.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private PlayerDTO convertToDTO(Player player) {
        return new PlayerDTO(
                player.getId(),
                player.getTeamNumber(),
                player.getPosition(),
                player.getFullName(),
                player.getTeamId()
        );
    }
}
