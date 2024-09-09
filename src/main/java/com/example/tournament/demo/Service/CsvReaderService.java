package com.example.tournament.demo.Service;

import com.example.tournament.demo.Model.Match;
import com.example.tournament.demo.Model.Player;
import com.example.tournament.demo.Model.Team;
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

    // Нов метод за четене на мачове
    public List<Match> readMatchesCsv(String filePath) {
        List<Match> matches = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Пропускаме заглавния ред
            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // Разделяме данните по запетая
                Match match = new Match(Long.parseLong(values[0]), Long.parseLong(values[1]),
                        Long.parseLong(values[2]), values[3], values[4]);
                matches.add(match);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return matches;
    }

    // Метод за четене на играчи
    public List<Player> readPlayersCsv(String filePath) {
        List<Player> players = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Пропускаме заглавния ред
            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // Разделяме данните по запетая
                Player player = new Player(
                        Long.parseLong(values[0]), // ID
                        Integer.parseInt(values[1]), // TeamNumber
                        values[2], // Position
                        values[3], // FullName
                        Long.parseLong(values[4]) // TeamID
                );
                players.add(player);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return players;
    }

}
