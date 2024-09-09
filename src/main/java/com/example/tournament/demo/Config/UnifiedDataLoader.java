package com.example.tournament.demo.Config;

import com.example.tournament.demo.Model.Match;
import com.example.tournament.demo.Model.Player;
import com.example.tournament.demo.Repository.MatchRepository;
import com.example.tournament.demo.Repository.PlayerRepository;
import com.example.tournament.demo.Service.CsvReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UnifiedDataLoader implements CommandLineRunner {
    private final MatchRepository matchRepository;
    private final PlayerRepository playerRepository;
    private final CsvReaderService csvReaderService;

    @Autowired
    public UnifiedDataLoader(MatchRepository matchRepository, PlayerRepository playerRepository, CsvReaderService csvReaderService) {
        this.matchRepository = matchRepository;
        this.playerRepository = playerRepository;
        this.csvReaderService = csvReaderService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadPlayers();
        //loadMatches();
    }

    // Зареждане на играчи
    private void loadPlayers() {
        String playersFilePath = "src/main/resources/csv_files/players.csv"; // Замени с реалния път
        List<Player> players = csvReaderService.readPlayersCsv(playersFilePath);
        playerRepository.saveAll(players);
    }

    // Зареждане на мачове
//    private void loadMatches() {
//        String matchesFilePath = "src/main/resources/csv_files/matches.csv"; // Замени с реалния път
//        List<Match> matches = csvReaderService.readMatchesCsv(matchesFilePath);
//        matchRepository.saveAll(matches);
//    }
}
