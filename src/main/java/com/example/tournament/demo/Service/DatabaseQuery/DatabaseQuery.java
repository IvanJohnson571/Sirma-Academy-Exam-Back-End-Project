package com.example.tournament.demo.Service.DatabaseQuery;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseQuery {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void runQuery() {
        String sql = "SELECT * FROM TEAM";
        jdbcTemplate.queryForList(sql).forEach(row -> System.out.println(row));
    }

}
