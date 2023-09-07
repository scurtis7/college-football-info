package com.scurtis.server.model;

import java.util.List;
import lombok.Data;

@Data
public class Team {

    private int id;
    private String school;
    private String mascot;
    private String abbreviation;
    private String alt_name_1;
    private String alt_name_2;
    private String alt_name_3;
    private String classification;
    private String conference;
    private String division;
    private String color;
    private String alt_color;
    private List<String> logos;
    private String twitter;
    private Location location;

}


