package com.scurtis.server.controller;

import com.scurtis.server.model.Team;
import com.scurtis.server.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "teams")
public class TeamsController {

    private final TeamService teamService;

    @GetMapping
    public Flux<Team> getTeams() {
        log.debug("getTeams()");
        return teamService.getAllTeams();
    }

    @GetMapping(path = "conference/{conference}")
    public Flux<Team> getTeamsByConference(@PathVariable String conference) {
        log.debug("getTeamsByConference()");
        return teamService.getTeamsByConference(conference);
    }

}
