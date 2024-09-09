package com.example.tournament.demo.Repository.TeamRepository;

import com.example.tournament.demo.Model.Team.Team;
import org.springframework.data.jpa.repository.*;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
