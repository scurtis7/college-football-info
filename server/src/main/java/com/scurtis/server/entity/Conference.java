package com.scurtis.server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Conference implements Persistable<Integer> {

    @Id
    private Integer id;
    private String name;
    private String shortName;
    private String abbreviation;
    private String classification;

    @Transient
    private boolean newRecord = false;

    @Override
    public boolean isNew() {
        return newRecord;
    }

}
