package com.example.tournament.demo.Controller;
import com.example.tournament.demo.DTO.MatchRequest;
import com.example.tournament.demo.Exceptions.Dates.InvalidDateFormatException;
import com.example.tournament.demo.Exceptions.Match.MatchNotFoundException;
import com.example.tournament.demo.Model.Match;
import com.example.tournament.demo.Repository.MatchRepository;
import com.example.tournament.demo.Util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    private final MatchRepository matchRepository;

    @Autowired
    public MatchController(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @GetMapping
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    @PostMapping("/create-match")
    public ResponseEntity<Match> createMatch(@RequestBody MatchRequest matchRequest) {
        try {
            Match match = new Match();
            match.setATeamId(matchRequest.getATeamId()); // Използваме setATeamId
            match.setBTeamId(matchRequest.getBTeamId()); // Използваме setBTeamId
            DateUtil.parseDate(matchRequest.getDate());  // Валидация на формата на датата
            match.setDate(matchRequest.getDate());       // Датата остава String
            match.setScore(matchRequest.getScore());
            matchRepository.save(match);
            return new ResponseEntity<>(match, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            throw new InvalidDateFormatException(matchRequest.getDate());
        }
    }

    @PutMapping("/rename-match/{id}")
    public ResponseEntity<Match> renameMatch(@PathVariable Long id, @RequestBody String newScore) {
        Optional<Match> matchOptional = matchRepository.findById(id);
        if (matchOptional.isPresent()) {
            Match match = matchOptional.get();
            match.setScore(newScore);
            matchRepository.save(match);
            return new ResponseEntity<>(match, HttpStatus.OK);
        } else {
            throw new MatchNotFoundException(id);
        }
    }

    @PutMapping("/update-match/{id}")
    public ResponseEntity<Match> updateMatch(@PathVariable Long id, @RequestBody MatchRequest matchRequest) {
        Optional<Match> matchOptional = matchRepository.findById(id);
        if (matchOptional.isPresent()) {
            Match match = matchOptional.get();
            match.setATeamId(matchRequest.getATeamId()); // Използваме setATeamId
            match.setBTeamId(matchRequest.getBTeamId()); // Използваме setBTeamId
            DateUtil.parseDate(matchRequest.getDate());  // Валидация на датата
            match.setDate(matchRequest.getDate());       // Датата остава String
            match.setScore(matchRequest.getScore());
            matchRepository.save(match);
            return new ResponseEntity<>(match, HttpStatus.OK);
        } else {
            throw new MatchNotFoundException(id);
        }
    }

    @DeleteMapping("/delete-match/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
        Optional<Match> matchOptional = matchRepository.findById(id);
        if (matchOptional.isPresent()) {
            matchRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            throw new MatchNotFoundException(id);
        }
    }

}