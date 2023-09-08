package com.scurtis.server.controller;

import com.scurtis.server.model.Team;
import com.scurtis.server.service.TeamService;
import java.net.URISyntaxException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TeamsController {

    private final TeamService teamService;

    @GetMapping(path = "teams")
    public Flux<Team> getTeams() throws URISyntaxException {
        log.debug("getTeams()");
        return teamService.getAllTeams();
    }

    @GetMapping(path = "teams/conference/{conference}")
    public Flux<Team> getTeamsByConference(@PathVariable String conference) throws URISyntaxException {
        log.debug("getTeamsByConference()");
        return teamService.getTeamsByConference(conference);
    }

}
