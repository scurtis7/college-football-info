package com.scurtis.server.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

@Data
public class Team implements Persistable<Integer> {

    @Id
    private Integer id;
    private String school;
    private String mascot;
    private String abbreviation;
    private String alternateName1;
    private String alternateName2;
    private String alternateName3;
    private String classification;
    private String conference;
    private String division;
    private String color;
    private String alternateColor;
    private String logos;
    private String twitter;
    private Integer venueId;

    @Transient
    private boolean newRecord = false;

    @Override
    public boolean isNew() {
        return newRecord;
    }

}
