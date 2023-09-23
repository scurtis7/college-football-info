package com.scurtis.server.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VenueDto {

    private int id;
    private String name;
    private int capacity;
    private boolean grass;
    private String city;
    private String state;
    private String zip;
    private String country_code;
    private String elevation;
    private int year_constructed;
    private boolean dome;
    private Loc location;
    private String timezone;

    @Getter
    @Setter
    public static class Loc {
        private double x;
        private double y;
    }

}
