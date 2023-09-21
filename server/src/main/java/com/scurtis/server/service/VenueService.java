package com.scurtis.server.service;

import com.scurtis.server.config.CfbConfig;
import com.scurtis.server.entity.Venue;
import com.scurtis.server.model.VenueDto;
import com.scurtis.server.repository.VenueRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Slf4j
@Service
@RequiredArgsConstructor
public class VenueService {

    private final VenueRepository venueRepository;

    private final CfbConfig cfbConfig;
    private final WebClient webClient;

    public Flux<VenueDto> getAllVenues() {
        log.debug("VenueService.getAllVenues()");
        return webClient.get()
            .uri(cfbConfig.getBaseUrl() + "venues")
            .header("Authorization", "Bearer " + cfbConfig.getApiKey())
            .retrieve()
            .bodyToFlux(VenueDto.class);
    }

    public void saveVenues() {
        List<VenueDto> venueDtos = getAllVenues().collectList().block();
        if (venueDtos != null) {
            List<Venue> venues = venueDtos
                .stream()
                .map(this::convertDtoToEntity).toList();
            venueRepository.saveAll(venues);
        }
    }

    private Venue convertDtoToEntity(VenueDto dto) {
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
        return venue;
    }

}
