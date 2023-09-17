package com.scurtis.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class TeamDto {

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
    private List<String> logos;
    private String twitter;
    @JsonProperty("location")
    private VenueDto venueDto;

}


