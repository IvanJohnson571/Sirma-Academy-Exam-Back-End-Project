package com.example.tournament.demo.Repository;

import com.example.tournament.demo.Model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByIdBetween(Long startId, Long endId);
}
