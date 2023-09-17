package com.scurtis.server.service;

import com.scurtis.server.config.CfbConfig;
import com.scurtis.server.model.Team;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamService {

    private final CfbConfig cfbConfig;
    private final WebClient webClient;

    public Flux<Team> getAllTeams() {
        log.debug("TeamService.getAllTeams()");
        return webClient.get()
            .uri(cfbConfig.getBaseUrl() + "teams")
            .header("Authorization", "Bearer " + cfbConfig.getApiKey())
            .retrieve()
            .bodyToFlux(Team.class);
    }

    public Flux<Team> getTeamsByConference(String conference) {
        log.debug("TeamService.getTeamsByConference() -> conference = {}", conference);
        return webClient.get()
            .uri(cfbConfig.getBaseUrl() + "teams?conference=" + conference)
            .header("Authorization", "Bearer " + cfbConfig.getApiKey())
            .retrieve()
            .bodyToFlux(Team.class);
    }

}
