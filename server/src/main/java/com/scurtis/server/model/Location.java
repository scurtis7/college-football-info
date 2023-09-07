package com.scurtis.server.model;

import lombok.Data;

@Data
public class Location {

    private int venue_id;
    private String name;
    private String city;
    private String state;
    private String zip;
    private String country_code;
    private String timezone;
    private double latitude;
    private double longitude;
    private double elevation;
    private int capacity;
    private int year_constructed;
    private boolean grass;
    private boolean dome;

}
