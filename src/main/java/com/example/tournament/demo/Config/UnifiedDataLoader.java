package com.example.tournament.demo.Config;

import com.example.tournament.demo.Model.Match;
import com.example.tournament.demo.Model.Player;
import com.example.tournament.demo.Model.Records;
import com.example.tournament.demo.Repository.MatchRepository;
import com.example.tournament.demo.Repository.PlayerRepository;
import com.example.tournament.demo.Repository.RecordsRepository;
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
    private final RecordsRepository recordsRepository;

    @Autowired
    public UnifiedDataLoader(MatchRepository matchRepository, PlayerRepository playerRepository, CsvReaderService csvReaderService, RecordsRepository recordsRepository) {
        this.matchRepository = matchRepository;
        this.playerRepository = playerRepository;
        this.csvReaderService = csvReaderService;
        this.recordsRepository = recordsRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadPlayers();
        loadRecords();
        //loadMatches();
    }

    private void loadPlayers() {
        String playersFilePath = "src/main/resources/csv_files/players.csv";
        List<Player> players = csvReaderService.readPlayersCsv(playersFilePath);
        playerRepository.saveAll(players);
    }

    private void loadRecords() {
        String recordsFilePath = "src/main/resources/csv_files/records.csv";
        List<Records> records = csvReaderService.readRecordsCsv(recordsFilePath);
        recordsRepository.saveAll(records);
    }

//    private void loadMatches() {
//        String matchesFilePath = "src/main/resources/csv_files/matches.csv";
//        List<Match> matches = csvReaderService.readMatchesCsv(matchesFilePath);
//        matchRepository.saveAll(matches);
//    }
}
