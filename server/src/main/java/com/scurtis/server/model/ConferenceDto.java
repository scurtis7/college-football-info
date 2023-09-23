package com.scurtis.server.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConferenceDto {

    private int id;
    private String name;
    private String short_name;
    private String abbreviation;
    private String classification;

}
