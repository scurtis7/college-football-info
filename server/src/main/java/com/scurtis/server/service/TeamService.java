package com.scurtis.server.service;

import com.scurtis.server.config.CfbConfig;
import com.scurtis.server.entity.Team;
import com.scurtis.server.model.TeamDto;
import com.scurtis.server.repository.TeamRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

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
        List<TeamDto> teamDtos = getAllTeams().collectList().block();
        if (teamDtos != null) {
            List<Team> teams = teamDtos
                .stream()
                .map(this::convertDtoToEntity).toList();
            teamRepository.saveAll(teams);
        }
    }

    private Team convertDtoToEntity(TeamDto dto) {
        Team team = new Team();
        team.setId(dto.getId());
        team.setSchool(dto.getSchool());
        team.setMascot(dto.getMascot());
        team.setAbbreviation(dto.getAbbreviation());
        team.setAlternateName1(dto.getAlt_name1());
        team.setAlternateName2(dto.getAlt_name2());
        team.setAlternateName3(dto.getAlt_name3());
        team.setClassification(dto.getClassification());
        team.setConference(dto.getConference());
        team.setDivision(dto.getDivision());
        team.setColor(dto.getColor());
        team.setAlternateColor(dto.getAlt_color());
        team.setLogos(StringUtils.join(dto.getLogos(), ", "));
        team.setTwitter(dto.getTwitter());
        team.setVenueId(dto.getLocationDto().getVenue_id());
        return team;
    }

}
