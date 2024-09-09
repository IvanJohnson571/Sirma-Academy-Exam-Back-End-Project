package com.example.tournament.demo.Repository;
import com.example.tournament.demo.Model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}
