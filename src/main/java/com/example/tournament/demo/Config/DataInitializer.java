package com.example.tournament.demo.Config;

import com.example.tournament.demo.Model.Team;
import com.example.tournament.demo.Repository.TeamRepository;
import com.example.tournament.demo.Service.CsvReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CsvReaderService csvReaderService;

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public void run(String... args) throws Exception {
        // Четене на данни от CSV файла
        List<Team> teams = csvReaderService.readTeamsCsv("src/main/resources/csv_files/teams.csv");

        // Записване на отборите в H2 базата данни
        for (Team team : teams) {
            teamRepository.save(team);
        }

        // Потвърждение в конзолата
        System.out.println("Teams have been initialized in the database!");
    }

}
