package com.example.tournament.demo.Repository;

import com.example.tournament.demo.Model.Team;
import org.springframework.data.jpa.repository.*;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
