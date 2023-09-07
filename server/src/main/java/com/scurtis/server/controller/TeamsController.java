package com.scurtis.server.controller;

import com.scurtis.server.model.Team;
import com.scurtis.server.service.TeamService;
import java.net.URISyntaxException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class TeamsController {

    private final TeamService teamService;

    @GetMapping(path = "teams")
    public Flux<Team> getTeams() throws URISyntaxException {
        return teamService.getAllTeams();
    }

}
