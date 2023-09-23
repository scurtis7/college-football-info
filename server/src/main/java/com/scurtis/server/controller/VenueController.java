package com.scurtis.server.controller;

import com.scurtis.server.model.VenueDto;
import com.scurtis.server.service.VenueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "venues")
public class VenueController {

    private final VenueService venueService;

    @GetMapping
    public Flux<VenueDto> getVenues() {
        log.debug("getVenues()");
        return venueService.getAllVenues();
    }

    @GetMapping(path = "save")
    public void saveConferences() {
        venueService.saveVenues();
    }

}
