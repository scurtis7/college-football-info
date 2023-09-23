package com.scurtis.server.service;

import com.scurtis.server.config.CfbConfig;
import com.scurtis.server.model.FpiDto;
import com.scurtis.server.model.SpDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Slf4j
@Service
@RequiredArgsConstructor
public class FpiService {

    private final CfbConfig cfbConfig;
    private final WebClient webClient;

    public Flux<FpiDto> getFpiByYear(int year) {
        log.debug("FpiService.getFpiByYear() -> year = {}", year);
        return webClient.get()
            .uri(cfbConfig.getBaseUrl() + "ratings/fpi?year=" + year)
            .header("Authorization", "Bearer " + cfbConfig.getApiKey())
            .retrieve()
            .bodyToFlux(FpiDto.class);
    }

    public Flux<SpDto> getSpByYear(int year) {
        log.debug("FpiService.getSpByYear() -> year = {}", year);
        return webClient.get()
            .uri(cfbConfig.getBaseUrl() + "ratings/sp?year=" + year)
            .header("Authorization", "Bearer " + cfbConfig.getApiKey())
            .retrieve()
            .bodyToFlux(SpDto.class);
    }

}
