package com.scurtis.server.service;

import com.scurtis.server.config.CfbConfig;
import com.scurtis.server.model.GameStatsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatsService {

    private final CfbConfig cfbConfig;
    private final WebClient webClient;

    public Flux<GameStatsDto> getGameStats(int year, String team, int week) {
        log.debug("StatsService.getGameStats() -> year:{}, team:{}, week:{}", year, team, week);
        return webClient.get()
            .uri(cfbConfig.getBaseUrl() + "stats/game/advanced?year=" + year + "&team=" + team + "&week=" + week + "&excludeGarbageTime=true")
            .header("Authorization", "Bearer " + cfbConfig.getApiKey())
            .retrieve()
            .bodyToFlux(GameStatsDto.class);
    }

}
