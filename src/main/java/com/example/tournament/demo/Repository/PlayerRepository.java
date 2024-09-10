package com.example.tournament.demo.Repository;
import com.example.tournament.demo.Model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    // Метод за намиране на всички играчи по ID на отбор
    List<Player> findByTeamId(Long teamId);
}
