package com.scurtis.server.util;

import com.scurtis.server.entity.Conference;
import com.scurtis.server.model.ConferenceDto;
import org.springframework.stereotype.Service;

@Service
public class ConferenceConverter {

    public ConferenceDto toDto(Conference entity) {
        return new ConferenceDto(entity.getId(), entity.getName(), entity.getShortname(), entity.getAbbreviation(), entity.getClassification());
    }

    public Conference toEntity(ConferenceDto dto) {
        return new Conference(dto.getId(), dto.getName(), dto.getShort_name(), dto.getAbbreviation(), dto.getClassification(), true);
    }

}
