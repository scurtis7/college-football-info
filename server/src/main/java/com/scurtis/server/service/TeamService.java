package com.scurtis.server.service;

import com.scurtis.server.config.CfbConfig;
import com.scurtis.server.model.Team;
import java.net.URI;
import java.net.URISyntaxException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final CfbConfig cfbConfig;
    private final WebClient webClient;

    public Flux<Team> getAllTeams() throws URISyntaxException {
        return webClient.get()
            .uri(new URI(cfbConfig.getBaseUrl() + "teams"))
            .header("Authorization", "Bearer " + cfbConfig.getApiKey())
            .retrieve()
            .bodyToFlux(Team.class);
    }

    public Flux<Team> getTeamsByConference(String conference) throws URISyntaxException {
        return webClient.get()
            .uri(new URI(cfbConfig.getBaseUrl() + "teams?conference=" + conference))
            .header("Authorization", "Bearer " + cfbConfig.getApiKey())
            .retrieve()
            .bodyToFlux(Team.class);
    }

}
