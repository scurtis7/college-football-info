package com.scurtis.server.service;

import com.scurtis.server.config.CfbConfig;
import com.scurtis.server.entity.Venue;
import com.scurtis.server.model.VenueDto;
import com.scurtis.server.repository.VenueRepository;
import com.scurtis.server.util.VenueConverter;
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
    private final VenueConverter converter;

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
        venueRepository.saveAll(getAllVenues().map(dto -> converter.toEntity(dto, true))).subscribe();
    }

}
