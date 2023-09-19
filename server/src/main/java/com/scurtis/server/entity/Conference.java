package com.scurtis.server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Conference {

    @Id
    private Integer id;
    private String name;
    private String shortName;
    private String abbreviation;
    private String classification;

}
