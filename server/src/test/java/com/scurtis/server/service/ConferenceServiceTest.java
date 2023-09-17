package com.scurtis.server.service;

import com.scurtis.server.config.CfbConfig;
import com.scurtis.server.model.Conference;
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
class ConferenceServiceTest {

    private ConferenceService conferenceService;

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
        conferenceService = spy(new ConferenceService(cfbConfigMock, webClientMock));
    }

    @AfterEach
    void afterEach() {
        verifyNoMoreInteractions(conferenceService);
    }

    @Test
    void testGetAllConferencesSuccess() {
        String baseUrl = "https://test/";
        Conference testConference = getConference();
        Flux<Conference> conferenceFlux = Flux.just(testConference);
        when(cfbConfigMock.getBaseUrl()).thenReturn(baseUrl);
        when(cfbConfigMock.getApiKey()).thenReturn("api-key");

        when(webClientMock.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(baseUrl + "conferences")).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.header("Authorization", "Bearer api-key")).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToFlux(Conference.class)).thenReturn(conferenceFlux);

        Flux<Conference> result = conferenceService.getAllConferences();

        StepVerifier.create(result)
            .expectNext(testConference)
            .expectComplete() // Verify the completion of the Flux
            .verify();
        verify(webClientMock).get();
        verify(cfbConfigMock).getBaseUrl();
        verify(cfbConfigMock).getApiKey();
        verify(conferenceService).getAllConferences();
    }

    private Conference getConference() {
        Conference conference = new Conference();
        conference.setId(1);
        conference.setName("Name");
        conference.setShort_name("Short Name");
        conference.setAbbreviation("Abbreviation");
        conference.setClassification("Classification");
        return conference;
    }

}
