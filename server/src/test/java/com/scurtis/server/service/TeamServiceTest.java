package com.scurtis.server.service;

import com.scurtis.server.config.CfbConfig;
import com.scurtis.server.model.Location;
import com.scurtis.server.model.Team;
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
        Team testTeam = getTeam();
        Flux<Team> teamFlux = Flux.just(testTeam);
        when(cfbConfigMock.getBaseUrl()).thenReturn(baseUrl);
        when(cfbConfigMock.getApiKey()).thenReturn("api-key");

        when(webClientMock.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(baseUrl + "teams")).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.header("Authorization", "Bearer api-key")).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToFlux(Team.class)).thenReturn(teamFlux);

        Flux<Team> result = teamService.getAllTeams();

        StepVerifier.create(result).expectNext(testTeam).expectComplete() // Verify the completion of the Flux
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
        Team testTeam = getTeam();
        Flux<Team> teamFlux = Flux.just(testTeam);
        when(cfbConfigMock.getBaseUrl()).thenReturn(baseUrl);
        when(cfbConfigMock.getApiKey()).thenReturn("api-key");

        when(webClientMock.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(baseUrl + "teams?conference=" + CONFERENCE)).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.header("Authorization", "Bearer api-key")).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToFlux(Team.class)).thenReturn(teamFlux);

        Flux<Team> result = teamService.getTeamsByConference(CONFERENCE);

        StepVerifier.create(result).expectNext(testTeam).expectComplete() // Verify the completion of the Flux
            .verify();
        verify(webClientMock).get();
        verify(cfbConfigMock).getBaseUrl();
        verify(cfbConfigMock).getApiKey();
        verify(teamService).getTeamsByConference(CONFERENCE);
    }

    private Team getTeam() {
        Team team = new Team();
        team.setId(1);
        team.setSchool("School");
        team.setMascot("Mascot");
        team.setAbbreviation("Abbreviation");
        team.setAlt_name_1("alt_name_1");
        team.setAlt_name_2("alt_name_2");
        team.setAlt_name_3("alt_name_3");
        team.setClassification("Classification");
        team.setConference("Conference");
        team.setDivision("Division");
        team.setAlt_color("alt_color");
        team.setLogos(Collections.singletonList("Logo"));
        team.setTwitter("Twitter");
        team.setLocation(getLocation());
        return team;
    }

    private Location getLocation() {
        Location location = new Location();
        location.setVenue_id(1);
        location.setName("Name");
        location.setCity("City");
        location.setState("State");
        location.setZip("Zip");
        location.setCountry_code("Country");
        location.setTimezone("Timezone");
        location.setLatitude(1);
        location.setLongitude(1);
        location.setElevation(1);
        location.setCapacity(1);
        location.setYear_constructed(1234);
        location.setGrass(true);
        location.setDome(false);
        return location;
    }

}
