package com.example.tournament.demo.Repository;

import com.example.tournament.demo.Model.Team;
import org.springframework.data.jpa.repository.*;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query("SELECT t FROM Team t WHERE t.id >= 37 AND t.id <= 44")
    List<Team> findSixteenFinals();

    @Query("SELECT t FROM Team t WHERE t.id >= 45 AND t.id <= 48")
    List<Team> findForthFinals();

    @Query("SELECT t FROM Team t WHERE t.id >= 49 AND t.id <= 50")
    List<Team> findSemiFinals();

    @Query("SELECT t FROM Team t WHERE t.id = 51")
    List<Team> findFinals();

    List<Team> findByGroupName(String groupName);

}
