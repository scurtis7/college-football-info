package com.scurtis.server.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

@Data
public class Venue implements Persistable<Integer> {

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

    @Transient
    private boolean newRecord = false;

    @Override
    public boolean isNew() {
        return newRecord;
    }

}
