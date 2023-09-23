package com.scurtis.server.service;

import com.scurtis.server.config.CfbConfig;
import com.scurtis.server.converter.TeamConverter;
import com.scurtis.server.model.TeamDto;
import com.scurtis.server.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final TeamConverter converter;
    private final CfbConfig cfbConfig;
    private final WebClient webClient;

    public Flux<TeamDto> getAllTeams() {
        log.debug("TeamService.getAllTeams()");
        return webClient.get()
            .uri(cfbConfig.getBaseUrl() + "teams")
            .header("Authorization", "Bearer " + cfbConfig.getApiKey())
            .retrieve()
            .bodyToFlux(TeamDto.class);
    }

    public Flux<TeamDto> getTeamsByConference(String conference) {
        log.debug("TeamService.getTeamsByConference() -> conference = {}", conference);
        return webClient.get()
            .uri(cfbConfig.getBaseUrl() + "teams?conference=" + conference)
            .header("Authorization", "Bearer " + cfbConfig.getApiKey())
            .retrieve()
            .bodyToFlux(TeamDto.class);
    }

    public void saveTeams() {
        teamRepository.saveAll(getAllTeams().map(dto -> converter.toEntity(dto, true))).subscribe();
    }

}
