package com.scurtis.server.service;

import com.scurtis.server.config.CfbConfig;
import com.scurtis.server.model.ConferenceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ConferenceService {

    private final CfbConfig cfbConfig;
    private final WebClient webClient;

    public Flux<ConferenceDto> getAllConferences() {
        return webClient.get()
            .uri(cfbConfig.getBaseUrl() + "conferences")
            .header("Authorization", "Bearer " + cfbConfig.getApiKey())
            .retrieve()
            .bodyToFlux(ConferenceDto.class);
    }

}
