package com.scurtis.server.model;

import lombok.Data;

@Data
public class ConferenceDto {

    private int id;
    private String name;
    private String short_name;
    private String abbreviation;
    private String classification;

}
