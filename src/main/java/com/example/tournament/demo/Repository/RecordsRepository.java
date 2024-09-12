package com.example.tournament.demo.Repository;
import com.example.tournament.demo.Model.Records;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RecordsRepository extends JpaRepository<Records, Long> {

    List<Records> findByPlayerId(Long playerId);

    List<Records> findByPlayerIdInAndFromMinutes(List<Long> playerIds, int fromMinutes);

}