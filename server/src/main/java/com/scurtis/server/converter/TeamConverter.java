package com.scurtis.server.converter;

import com.scurtis.server.entity.Team;
import com.scurtis.server.model.TeamDto;
import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class TeamConverter {

    public TeamDto toDto(Team entity) {
        TeamDto dto = new TeamDto();
        dto.setId(entity.getId() != null ? entity.getId() : 0);
        dto.setSchool(entity.getSchool());
        dto.setMascot(entity.getMascot());
        dto.setAbbreviation(entity.getAbbreviation());
        dto.setAlt_name1(entity.getAlternateName1());
        dto.setAlt_name2(entity.getAlternateName2());
        dto.setAlt_name3(entity.getAlternateName3());
        dto.setClassification(entity.getClassification());
        dto.setConference(entity.getConference());
        dto.setDivision(entity.getDivision());
        dto.setColor(entity.getColor());
        dto.setAlt_color(entity.getAlternateColor());
        dto.setLogos(Arrays.stream(StringUtils.split(entity.getLogos(), ",")).toList());
        dto.setTwitter(entity.getTwitter());
        dto.getLocation().setVenue_id(entity.getVenueId());
        return dto;
    }

    public Team toEntity(TeamDto dto, boolean isNew) {
        Team team = new Team();
        team.setId(dto.getId());
        team.setSchool(dto.getSchool());
        team.setMascot(dto.getMascot());
        team.setAbbreviation(dto.getAbbreviation());
        team.setAlternateName1(dto.getAlt_name1());
        team.setAlternateName2(dto.getAlt_name2());
        team.setAlternateName3(dto.getAlt_name3());
        team.setClassification(dto.getClassification());
        team.setConference(dto.getConference());
        team.setDivision(dto.getDivision());
        team.setColor(dto.getColor());
        team.setAlternateColor(dto.getAlt_color());
        team.setLogos(StringUtils.join(dto.getLogos(), ", "));
        team.setTwitter(dto.getTwitter());
        team.setVenueId(dto.getLocation().getVenue_id());
        team.setNewRecord(isNew);
        return team;
    }

}
