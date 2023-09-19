package com.scurtis.server.service;

import com.scurtis.server.config.CfbConfig;
import com.scurtis.server.entity.Conference;
import com.scurtis.server.model.ConferenceDto;
import com.scurtis.server.repository.ConferenceRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ConferenceService {

    private final ConferenceRepository conferenceRepository;

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
        List<ConferenceDto> conferenceDtos = getAllConferences().collectList().block();
        if (conferenceDtos != null) {
            List<Conference> conferences = conferenceDtos
                .stream()
                .map(this::convertDtoToEntity).toList();
            conferenceRepository.saveAll(conferences);
        }
    }

    private Conference convertDtoToEntity(ConferenceDto dto) {
        Conference conference = new Conference();
        conference.setId(dto.getId());
        conference.setName(dto.getName());
        conference.setShortName(dto.getShort_name());
        conference.setAbbreviation(dto.getAbbreviation());
        conference.setClassification(dto.getClassification());
        return conference;
    }

}
