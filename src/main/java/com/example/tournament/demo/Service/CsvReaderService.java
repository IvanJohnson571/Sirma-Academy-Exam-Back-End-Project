package com.example.tournament.demo.Service;

import com.example.tournament.demo.Model.Match;
import com.example.tournament.demo.Model.Player;
import com.example.tournament.demo.Model.Records;
import com.example.tournament.demo.Model.Team;
import com.example.tournament.demo.Util.CsvHelper;
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
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Team team = new Team(Long.parseLong(values[0]), values[1], values[2], values[3]);
                teams.add(team);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return teams;
    }

    public List<Match> readMatchesCsv(String filePath) {
        List<Match> matches = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Match match = new Match(Long.parseLong(values[0]), Long.parseLong(values[1]),
                        Long.parseLong(values[2]), values[3], values[4]);
                matches.add(match);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return matches;
    }

    public List<Player> readPlayersCsv(String filePath) {
        List<Player> players = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Player player = new Player(
                        Long.parseLong(values[0]),
                        Integer.parseInt(values[1]),
                        values[2],
                        values[3],
                        Long.parseLong(values[4])
                );
                players.add(player);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return players;
    }

    public List<Records> readRecordsCsv(String filePath) {
        List<Records> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {

                String[] values = line.split(",");

                Long id = CsvHelper.parseLongOrNull(values[0]);
                Long playerId = CsvHelper.parseLongOrNull(values[1]);
                Long matchId = CsvHelper.parseLongOrNull(values[2]);
                Integer fromMinutes = CsvHelper.parseIntOrNull(values[3]);
                Integer toMinutes = CsvHelper.parseIntOrNull(values[4]);

                Records record = new Records(id, playerId, matchId, fromMinutes, toMinutes);
                records.add(record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

}