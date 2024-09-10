package com.example.tournament.demo.Repository;

import com.example.tournament.demo.Model.Records;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordsRepository extends JpaRepository<Records, Long> {
}
