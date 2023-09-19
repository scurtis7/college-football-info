package com.scurtis.server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Venue {

    @Id
    private Integer id;
    private String name;
    private String city;
    private String state;
    private String zip;
    private String countryCode;
    private String timezone;
    private Double latitude;
    private Double longitude;
    private String elevation;
    private Integer capacity;
    private Integer yearConstructed;
    private Boolean grass;
    private Boolean dome;

}
