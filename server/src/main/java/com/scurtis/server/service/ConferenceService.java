package com.scurtis.server.service;

import com.scurtis.server.config.CfbConfig;
import com.scurtis.server.entity.Conference;
import com.scurtis.server.model.ConferenceDto;
import com.scurtis.server.repository.ConferenceRepository;
import com.scurtis.server.util.ConferenceConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ConferenceService {

    private final ConferenceRepository conferenceRepository;
    private final ConferenceConverter converter;

    private final CfbConfig cfbConfig;
    private final WebClient webClient;

    public Flux<ConferenceDto> getAllConferences() {
        return webClient.get()
            .uri(cfbConfig.getBaseUrl() + "conferences")
            .header("Authorization", "Bearer " + cfbConfig.getApiKey())
            .retrieve()
            .bodyToFlux(ConferenceDto.class);
    }

    public void saveConferences() {
        conferenceRepository.saveAll(getAllConferences().map(converter::toEntity)).subscribe();
    }

}
