package com.example.tournament.demo.Model.Team;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Team {

    @Id
    private Long id;
    private String name;
    private String managerFullName;
    private String groupName;  // Променяме 'group' на 'groupName', защото 'group' е запазена дума в SQL

    public Team() {
    }

    public Team(Long id, String name, String managerFullName, String groupName) {
        this.id = id;
        this.name = name;
        this.managerFullName = managerFullName;
        this.groupName = groupName;
    }

    // Getter-и и Setter-и
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManagerFullName() {
        return managerFullName;
    }

    public void setManagerFullName(String managerFullName) {
        this.managerFullName = managerFullName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}

