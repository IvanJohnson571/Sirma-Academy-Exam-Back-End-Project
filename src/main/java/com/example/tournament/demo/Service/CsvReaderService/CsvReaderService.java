package com.example.tournament.demo.Service.CsvReaderService;

import com.example.tournament.demo.Model.Team.Team;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvReaderService {

    public List<Team> readTeamsCsv(String filePath) {
        List<Team> teams = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Пропускаме заглавния ред
            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // Разделяме данните по запетая
                Team team = new Team(Long.parseLong(values[0]), values[1], values[2], values[3]);
                teams.add(team);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return teams;
    }

}
