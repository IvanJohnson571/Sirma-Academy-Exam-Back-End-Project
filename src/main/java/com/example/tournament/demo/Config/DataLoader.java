package com.example.tournament.demo.Config;

import com.example.tournament.demo.Model.Match;
import com.example.tournament.demo.Repository.MatchRepository;
import com.example.tournament.demo.Service.CsvReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final MatchRepository matchRepository;
    private final CsvReaderService csvReaderService;

    @Autowired
    public DataLoader(MatchRepository matchRepository, CsvReaderService csvReaderService) {
        this.matchRepository = matchRepository;
        this.csvReaderService = csvReaderService;
    }

    @Override
    public void run(String... args) throws Exception {
        String filePath = "src/main/resources/csv_files/matches.csv";
        List<Match> matches = csvReaderService.readMatchesCsv(filePath);
        matchRepository.saveAll(matches);
        // Потвърждение в конзолата
        System.out.println("Matches have been initialized in the database!");
    }
}
