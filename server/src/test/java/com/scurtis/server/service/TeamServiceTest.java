package com.scurtis.server.service;

import com.scurtis.server.config.CfbConfig;
import com.scurtis.server.converter.TeamConverter;
import com.scurtis.server.model.TeamDto;
import com.scurtis.server.repository.TeamRepository;
import java.util.Collections;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class TeamServiceTest {

    private TeamService teamService;

    @Mock
    private TeamRepository teamRepositoryMock;
    @Mock
    private TeamConverter converterMock;
    @Mock
    private CfbConfig cfbConfigMock;
    @Mock
    private WebClient webClientMock;
    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpecMock;
    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpecMock;
    @Mock
    private WebClient.ResponseSpec responseSpecMock;

    @BeforeEach
    void setup() {
        teamService = spy(new TeamService(teamRepositoryMock, converterMock, cfbConfigMock, webClientMock));
    }

    @AfterEach
    void afterEach() {
        verifyNoMoreInteractions(teamService);
    }

    @Test
    void testGetAllTeamsSuccess() {
        String baseUrl = "https://test/";
        TeamDto testTeamDto = getTeam();
        Flux<TeamDto> teamFlux = Flux.just(testTeamDto);
        when(cfbConfigMock.getBaseUrl()).thenReturn(baseUrl);
        when(cfbConfigMock.getApiKey()).thenReturn("api-key");

        when(webClientMock.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(baseUrl + "teams")).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.header("Authorization", "Bearer api-key")).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToFlux(TeamDto.class)).thenReturn(teamFlux);

        Flux<TeamDto> result = teamService.getAllTeams();

        StepVerifier.create(result).expectNext(testTeamDto).expectComplete() // Verify the completion of the Flux
            .verify();
        verify(webClientMock).get();
        verify(cfbConfigMock).getBaseUrl();
        verify(cfbConfigMock).getApiKey();
        verify(teamService).getAllTeams();
    }

    @Test
    void testGetTeamsByConferenceSuccess() {
        String CONFERENCE = "conference";
        String baseUrl = "https://test/";
        TeamDto testTeamDto = getTeam();
        Flux<TeamDto> teamFlux = Flux.just(testTeamDto);
        when(cfbConfigMock.getBaseUrl()).thenReturn(baseUrl);
        when(cfbConfigMock.getApiKey()).thenReturn("api-key");

        when(webClientMock.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(baseUrl + "teams?conference=" + CONFERENCE)).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.header("Authorization", "Bearer api-key")).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToFlux(TeamDto.class)).thenReturn(teamFlux);

        Flux<TeamDto> result = teamService.getTeamsByConference(CONFERENCE);

        StepVerifier.create(result).expectNext(testTeamDto).expectComplete() // Verify the completion of the Flux
            .verify();
        verify(webClientMock).get();
        verify(cfbConfigMock).getBaseUrl();
        verify(cfbConfigMock).getApiKey();
        verify(teamService).getTeamsByConference(CONFERENCE);
    }

    private TeamDto getTeam() {
        TeamDto teamDto = new TeamDto();
        teamDto.setId(1);
        teamDto.setSchool("School");
        teamDto.setMascot("Mascot");
        teamDto.setAbbreviation("Abbreviation");
        teamDto.setAlt_name1("alt_name_1");
        teamDto.setAlt_name2("alt_name_2");
        teamDto.setAlt_name3("alt_name_3");
        teamDto.setClassification("Classification");
        teamDto.setConference("ConferenceDto");
        teamDto.setDivision("Division");
        teamDto.setAlt_color("alt_color");
        teamDto.setLogos(Collections.singletonList("Logo"));
        teamDto.setTwitter("Twitter");
        teamDto.setLocation(getLocation());
        return teamDto;
    }

    private TeamDto.Location getLocation() {
        TeamDto.Location location = new TeamDto.Location();
        location.setVenue_id(1);
        location.setName("Name");
        location.setCity("City");
        location.setState("State");
        location.setZip("Zip");
        location.setCountry_code("Country");
        location.setTimezone("Timezone");
        location.setLatitude(1d);
        location.setLongitude(1d);
        location.setElevation("1.1");
        location.setCapacity(1);
        location.setYear_constructed(1234);
        location.setGrass(true);
        location.setDome(false);
        return location;
    }

}
