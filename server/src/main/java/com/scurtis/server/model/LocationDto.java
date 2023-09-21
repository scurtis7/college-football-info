package com.scurtis.server.model;

import lombok.Data;

@Data
public class LocationDto {

    private Integer venue_id;
    private String name;
    private String city;
    private String state;
    private String zip;
    private String country_code;
    private String timezone;
    private Double latitude;
    private Double longitude;
    private String elevation;
    private Integer capacity;
    private Integer year_constructed;
    private Boolean grass;
    private Boolean dome;

}
