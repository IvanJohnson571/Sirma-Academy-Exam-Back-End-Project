package com.example.tournament.demo.Model;

public class TeamPoints {
    private Long id;
    private String name;
    private int points;
    private int goalsPlus;
    private int goalsOut;
    private int balance;

    public TeamPoints(Long id, String name, int points, int goalsPlus, int goalsOut) {
        this.id = id;
        this.name = name;
        this.points = points;
        this.goalsPlus = goalsPlus;
        this.goalsOut = goalsOut;
        this.balance = goalsPlus - goalsOut;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public int getGoalsPlus() {
        return goalsPlus;
    }

    public int getGoalsOut() {
        return goalsOut;
    }

    public int getBalance() {
        return balance;
    }

    public void setGoalsPlus(int goalsPlus) {
        this.goalsPlus = goalsPlus;
    }

    public void setGoalsOut(int goalsOut) {
        this.goalsOut = goalsOut;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}


