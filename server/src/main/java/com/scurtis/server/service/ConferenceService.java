package com.scurtis.server.service;

import com.scurtis.server.config.CfbConfig;
import com.scurtis.server.model.Conference;
import com.scurtis.server.model.Team;
import java.net.URI;
import java.net.URISyntaxException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ConferenceService {

    private final CfbConfig cfbConfig;
    private final WebClient webClient;

    public Flux<Conference> getAllConferences() throws URISyntaxException {
        return webClient.get()
                .uri(new URI(cfbConfig.getBaseUrl() + "conferences"))
                .header("Authorization", "Bearer " + cfbConfig.getApiKey())
                .retrieve()
                .bodyToFlux(Conference.class);
    }

}
