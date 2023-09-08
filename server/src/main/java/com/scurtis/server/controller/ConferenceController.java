package com.scurtis.server.controller;

import com.scurtis.server.model.Conference;
import com.scurtis.server.service.ConferenceService;
import java.net.URISyntaxException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "conferences")
public class ConferenceController {

    private final ConferenceService conferenceService;

    @GetMapping
    public Flux<Conference> getAllConferences() throws URISyntaxException {
        return conferenceService.getAllConferences();
    }

}