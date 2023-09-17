package com.scurtis.server.service;

import com.scurtis.server.config.CfbConfig;
import com.scurtis.server.model.VenueDto;
import com.scurtis.server.model.TeamDto;
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
        teamService = spy(new TeamService(cfbConfigMock, webClientMock));
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
        teamDto.setAlt_name_1("alt_name_1");
        teamDto.setAlt_name_2("alt_name_2");
        teamDto.setAlt_name_3("alt_name_3");
        teamDto.setClassification("Classification");
        teamDto.setConference("ConferenceDto");
        teamDto.setDivision("Division");
        teamDto.setAlt_color("alt_color");
        teamDto.setLogos(Collections.singletonList("Logo"));
        teamDto.setTwitter("Twitter");
        teamDto.setVenueDto(getLocation());
        return teamDto;
    }

    private VenueDto getLocation() {
        VenueDto venueDto = new VenueDto();
        venueDto.setVenue_id(1);
        venueDto.setName("Name");
        venueDto.setCity("City");
        venueDto.setState("State");
        venueDto.setZip("Zip");
        venueDto.setCountry_code("Country");
        venueDto.setTimezone("Timezone");
        venueDto.setLatitude(1);
        venueDto.setLongitude(1);
        venueDto.setElevation(1);
        venueDto.setCapacity(1);
        venueDto.setYear_constructed(1234);
        venueDto.setGrass(true);
        venueDto.setDome(false);
        return venueDto;
    }

}
