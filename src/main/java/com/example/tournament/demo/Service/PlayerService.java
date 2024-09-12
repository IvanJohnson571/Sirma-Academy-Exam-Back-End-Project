package com.example.tournament.demo.Service;
import com.example.tournament.demo.DTO.PlayerDTO;
import com.example.tournament.demo.Model.*;
import com.example.tournament.demo.Repository.MatchRepository;
import com.example.tournament.demo.Repository.PlayerRepository;
import com.example.tournament.demo.Repository.RecordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final RecordsRepository recordRepository;
    private final MatchRepository matchRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, RecordsRepository recordRepository, MatchRepository matchRepository) {
        this.playerRepository = playerRepository;
        this.recordRepository = recordRepository;
        this.matchRepository = matchRepository;
    }

    public List<PlayerDTO> getAllPlayers() {
        List<Player> players = playerRepository.findAll();
        return players.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private PlayerDTO convertToDTO(Player player) {
        return new PlayerDTO(
                player.getId(),
                player.getTeamNumber(),
                player.getPosition(),
                player.getFullName(),
                player.getTeamId()
        );
    }

    public Map<String, List<Player>> getPlayersByTwoTeams(Long teamAId, Long teamBId) {
        List<Player> teamAPlayers = playerRepository.findByTeamId(teamAId);
        List<Player> teamBPlayers = playerRepository.findByTeamId(teamBId);

        Map<String, List<Player>> teamsPlayers = new HashMap<>();
        teamsPlayers.put("TeamAPlayers", teamAPlayers);
        teamsPlayers.put("TeamBPlayers", teamBPlayers);

        return teamsPlayers;

    }

    public PlayerPairWithMatches findPlayersPlayedTogetherLongest() {
        Map<PlayerPair, Integer> totalPlayTimeByPair = new HashMap<>();
        Map<PlayerPair, List<MatchWithTime>> matchesByPair = new HashMap<>();

        List<Records> records = recordRepository.findAll();

        Map<Long, List<Records>> recordsByMatch = records.stream()
                .collect(Collectors.groupingBy(Records::getMatchId));

        for (Long matchId : recordsByMatch.keySet()) {
            List<Records> matchRecords = recordsByMatch.get(matchId);

            for (int i = 0; i < matchRecords.size(); i++) {
                Records playerARecord = matchRecords.get(i);
                Player playerA = playerRepository.findById(playerARecord.getPlayerId()).orElse(null);

                for (int j = i + 1; j < matchRecords.size(); j++) {
                    Records playerBRecord = matchRecords.get(j);
                    Player playerB = playerRepository.findById(playerBRecord.getPlayerId()).orElse(null);

                    if (playerA != null && playerB != null && !playerA.getTeamId().equals(playerB.getTeamId())) {
                        int overlapTime = calculateOverlap(playerARecord, playerBRecord);

                        if (overlapTime > 0) {
                            PlayerPair pair = new PlayerPair(playerA, playerB);

                            totalPlayTimeByPair.put(pair, totalPlayTimeByPair.getOrDefault(pair, 0) + overlapTime);

                            matchesByPair.computeIfAbsent(pair, k -> new ArrayList<>())
                                    .add(new MatchWithTime(matchRepository.findById(matchId).orElse(null), overlapTime));
                        }
                    }
                }
            }
        }

        PlayerPair longestPair = totalPlayTimeByPair.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow(() -> new RuntimeException("No pairs found"))
                .getKey();

        return new PlayerPairWithMatches(longestPair, matchesByPair.get(longestPair));
    }

    private int calculateOverlap(Records playerA, Records playerB) {
        int fromA = playerA.getFromMinutes();
        int toA = playerA.getToMinutes() != null ? playerA.getToMinutes() : 90;

        int fromB = playerB.getFromMinutes();
        int toB = playerB.getToMinutes() != null ? playerB.getToMinutes() : 90;

        int startOverlap = Math.max(fromA, fromB);
        int endOverlap = Math.min(toA, toB);

        if (startOverlap < endOverlap) {
            return endOverlap - startOverlap;
        } else {
            return 0;
        }
    }

    public List<PlayerWithMinutes> getPlayersByMinutesPlayed() {
        Map<Long, Integer> minutesPlayedByPlayer = new HashMap<>();

        List<Records> records = recordRepository.findAll();

        for (Records record : records) {
            int fromMinutes = record.getFromMinutes();
            int toMinutes = (record.getToMinutes() != null) ? record.getToMinutes() : 90;
            int minutesPlayed = toMinutes - fromMinutes;

            minutesPlayedByPlayer.put(record.getPlayerId(),
                    minutesPlayedByPlayer.getOrDefault(record.getPlayerId(), 0) + minutesPlayed);
        }

        List<PlayerWithMinutes> playersWithMinutes = minutesPlayedByPlayer.entrySet().stream()
                .map(entry -> {
                    Player player = playerRepository.findById(entry.getKey()).orElse(null);
                    return new PlayerWithMinutes(player, entry.getValue());
                })
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingInt(PlayerWithMinutes::getMinutesPlayed).reversed())
                .collect(Collectors.toList());

        return playersWithMinutes;
    }

    public PlayerDurationResponse getPlayerDuration(Long playerId) {

        Player player = playerRepository.findById(playerId).orElseThrow(() -> new RuntimeException("Player not found"));
        List<Records> playerRecords = recordRepository.findByPlayerId(playerId);

        long totalMinutes = playerRecords.stream()
                .mapToLong(record -> {
                    int fromMinutes = record.getFromMinutes();
                    int toMinutes = (record.getToMinutes() != null) ? record.getToMinutes() : 90;
                    return toMinutes - fromMinutes;
                })
                .sum();

        return new PlayerDurationResponse(totalMinutes, player);
    }

    public List<PlayerDurationResponse> getAllPlayerDurations() {

        List<Records> allRecords = recordRepository.findAll();
        Map<Long, Long> minutesPlayedByPlayer = new HashMap<>();

        for (Records record : allRecords) {
            int fromMinutes = record.getFromMinutes();
            int toMinutes = (record.getToMinutes() != null) ? record.getToMinutes() : 90;
            long minutesPlayed = toMinutes - fromMinutes;

            minutesPlayedByPlayer.put(record.getPlayerId(),
                    minutesPlayedByPlayer.getOrDefault(record.getPlayerId(), 0L) + minutesPlayed);
        }

        return minutesPlayedByPlayer.entrySet().stream()
                .map(entry -> {
                    Player player = playerRepository.findById(entry.getKey()).orElse(null);
                    return new PlayerDurationResponse(entry.getValue(), player);
                })
                .filter(response -> response.getPlayer() != null)
                .collect(Collectors.toList());
    }

    public List<PlayerDurationResponse> getTop10PlayerDurations() {
        List<PlayerDurationResponse> allPlayers = getAllPlayerDurations();

        return allPlayers.stream()
                .sorted(Comparator.comparingLong(PlayerDurationResponse::getDuration).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<PlayerPair> findPlayersWhoPlayedTogether() {

        List<PlayerDurationResponse> top10Players = getTop10PlayerDurations();

        for (int i = 0; i < top10Players.size(); i++) {

            for (int j = i + 1; j < top10Players.size(); j++) {

                Player playerA = top10Players.get(i).getPlayer();
                Player playerB = top10Players.get(j).getPlayer();

                List<MatchTogetherInfo> matchesAndTime = getMatchesAndTimePlayedTogether(playerA.getId(), playerB.getId());

                if (havePlayedTogetherInAllMatches(playerA.getId(), playerB.getId())) {

                    PlayerPair playerPair = new PlayerPair(playerA, playerB);
                    playerPair.setMatchesTogether(matchesAndTime);

                    return List.of(playerPair);

                }
            }
        }
        return List.of();
    }

    private boolean havePlayedTogetherInAllMatches(Long playerAId, Long playerBId) {
        List<Records> playerARecords = recordRepository.findByPlayerId(playerAId);
        List<Records> playerBRecords = recordRepository.findByPlayerId(playerBId);

        for (Records recordA : playerARecords) {
            boolean playedTogether = playerBRecords.stream().anyMatch(recordB ->
                    recordB.getMatchId().equals(recordA.getMatchId()) &&
                            isTimeOverlapping(recordA, recordB)
            );

            if (!playedTogether) {
                return false;
            }
        }
        return true;
    }

    // Matches played together
    public List<MatchTogetherInfo> getMatchesAndTimePlayedTogether(Long playerAId, Long playerBId) {
        List<Records> playerARecords = recordRepository.findByPlayerId(playerAId);
        List<Records> playerBRecords = recordRepository.findByPlayerId(playerBId);

        List<MatchTogetherInfo> matchesTogether = new ArrayList<>();

        for (Records recordA : playerARecords) {
            playerBRecords.stream()
                    .filter(recordB -> recordB.getMatchId().equals(recordA.getMatchId()) && isTimeOverlapping(recordA, recordB))
                    .forEach(recordB -> {
                        int togetherTime = getTogetherTime(recordA, recordB);
                        matchesTogether.add(new MatchTogetherInfo(recordA.getMatchId(), togetherTime));
                    });
        }

        return matchesTogether;
    }

    // Duration per match
    private int getTogetherTime(Records recordA, Records recordB) {
        int fromA = recordA.getFromMinutes();
        int toA = (recordA.getToMinutes() != null) ? recordA.getToMinutes() : 90;

        int fromB = recordB.getFromMinutes();
        int toB = (recordB.getToMinutes() != null) ? recordB.getToMinutes() : 90;

        int startTogether = Math.max(fromA, fromB);
        int endTogether = Math.min(toA, toB);

        return endTogether - startTogether;
    }

    private boolean isTimeOverlapping(Records recordA, Records recordB) {
        int fromA = recordA.getFromMinutes();
        int toA = (recordA.getToMinutes() != null) ? recordA.getToMinutes() : 90;

        int fromB = recordB.getFromMinutes();
        int toB = (recordB.getToMinutes() != null) ? recordB.getToMinutes() : 90;

        return fromA < toB && fromB < toA;
    }

    public PlayerDTO getPlayerById(Long id) {
        Player player = playerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Player not found for id: " + id));
        return convertToDTO(player);
    }

    public PlayerDTO createPlayer(PlayerDTO playerDTO) {
        Player player = convertToEntity(playerDTO);
        playerRepository.save(player);
        return convertToDTO(player);
    }

    public PlayerDTO updatePlayer(Long id, PlayerDTO playerDTO) {
        Player player = playerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Player not found for id: " + id));
        player.setTeamNumber(playerDTO.getTeamNumber());
        player.setPosition(playerDTO.getPosition());
        player.setFullName(playerDTO.getFullName());
        player.setTeamId(playerDTO.getTeamId());
        playerRepository.save(player);
        return convertToDTO(player);
    }

    public void deletePlayer(Long id) {
        Player player = playerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Player not found for id: " + id));
        playerRepository.delete(player);
    }

    private Player convertToEntity(PlayerDTO playerDTO) {
        Player player = new Player();
        player.setId(playerDTO.getId());
        player.setTeamNumber(playerDTO.getTeamNumber());
        player.setPosition(playerDTO.getPosition());
        player.setFullName(playerDTO.getFullName());
        player.setTeamId(playerDTO.getTeamId());
        return player;
    }

}