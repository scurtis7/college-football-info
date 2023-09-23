package com.scurtis.server.converter;

import com.scurtis.server.entity.Venue;
import com.scurtis.server.model.VenueDto;
import org.springframework.stereotype.Service;

@Service
public class VenueConverter {

    public VenueDto toDto(Venue entity) {
        VenueDto dto = new VenueDto();
        dto.setId(entity.getId() != null ? entity.getId() : 0);
        dto.setName(entity.getName());
        dto.setCity(entity.getCity());
        dto.setState(entity.getState());
        dto.setZip(entity.getZip());
        dto.setCountry_code(entity.getCountryCode());
        dto.setTimezone(entity.getTimezone());
        VenueDto.Loc loc = new VenueDto.Loc();
        loc.setX(entity.getLatitude());
        loc.setY(entity.getLongitude());
        dto.setLocation(loc);
        dto.setElevation(entity.getElevation());
        dto.setCapacity(entity.getCapacity());
        dto.setYear_constructed(entity.getYearConstructed());
        dto.setGrass(entity.getGrass());
        dto.setDome(entity.getDome());
        return dto;
    }

    public Venue toEntity(VenueDto dto, boolean isNew) {
        Venue venue = new Venue();
        venue.setId(dto.getId());
        venue.setName(dto.getName());
        venue.setCity(dto.getCity());
        venue.setState(dto.getState());
        venue.setZip(dto.getZip());
        venue.setCountryCode(dto.getCountry_code());
        venue.setTimezone(dto.getTimezone());
        if (dto.getLocation() != null) {
            venue.setLatitude(dto.getLocation().getX());
            venue.setLongitude(dto.getLocation().getY());
        } else {
            venue.setLatitude(0d);
            venue.setLongitude(0d);
        }
        venue.setElevation(dto.getElevation());
        venue.setCapacity(dto.getCapacity());
        venue.setYearConstructed(dto.getYear_constructed());
        venue.setGrass(dto.isGrass());
        venue.setDome(dto.isDome());
        venue.setNewRecord(isNew);
        return venue;
    }

}
