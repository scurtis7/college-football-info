package com.scurtis.server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Venue {

    @Id
    private int id;
    private String name;
    private String city;
    private String state;
    private String zip;
    private String countryCode;
    private String timezone;
    private double latitude;
    private double longitude;
    private String elevation;
    private int capacity;
    private int yearConstructed;
    private boolean grass;
    private boolean dome;

}
