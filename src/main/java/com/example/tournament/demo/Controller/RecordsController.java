package com.example.tournament.demo.Controller;

import com.example.tournament.demo.Model.Records;
import com.example.tournament.demo.Repository.RecordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/records")
public class RecordsController {

    private final RecordsRepository recordsRepository;

    @Autowired
    public RecordsController(RecordsRepository recordsRepository) {
        this.recordsRepository = recordsRepository;
    }

    @GetMapping
    public List<Records> getAllRecords() {
        return recordsRepository.findAll();
    }
}
