package com.scurtis.server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Team {

    @Id
    private int id;
    private String school;
    private String mascot;
    private String abbreviation;
    private String alt_name1;
    private String alt_name2;
    private String alt_name3;
    private String classification;
    private String conference;
    private String division;
    private String color;
    private String alt_color;
    private String logos;
    private String twitter;
    @OneToOne
    private Venue venue;

}
