package com.scurtis.server.service;

import com.scurtis.server.config.CfbConfig;
import com.scurtis.server.converter.ConferenceConverter;
import com.scurtis.server.model.ConferenceDto;
import com.scurtis.server.repository.ConferenceRepository;
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
    private ConferenceRepository conferenceRepositoryMock;
    @Mock
    private ConferenceConverter converterMock;
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
        conferenceService = spy(new ConferenceService(conferenceRepositoryMock, converterMock, cfbConfigMock, webClientMock));
    }

    @AfterEach
    void afterEach() {
        verifyNoMoreInteractions(conferenceRepositoryMock);
        verifyNoMoreInteractions(converterMock);
        verifyNoMoreInteractions(cfbConfigMock);
        verifyNoMoreInteractions(webClientMock);
        verifyNoMoreInteractions(requestHeadersUriSpecMock);
        verifyNoMoreInteractions(requestHeadersSpecMock);
        verifyNoMoreInteractions(responseSpecMock);
        verifyNoMoreInteractions(conferenceService);
    }

    @Test
    void testGetAllConferencesSuccess() {
        String baseUrl = "https://test/";
        ConferenceDto testConferenceDto = getConferenceDto();
        Flux<ConferenceDto> testConferenceDtoFlux = Flux.just(testConferenceDto);
        when(cfbConfigMock.getBaseUrl()).thenReturn(baseUrl);
        when(cfbConfigMock.getApiKey()).thenReturn("api-key");

        when(webClientMock.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(baseUrl + "conferences")).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.header("Authorization", "Bearer api-key")).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToFlux(ConferenceDto.class)).thenReturn(testConferenceDtoFlux);

        Flux<ConferenceDto> result = conferenceService.getAllConferences();

        StepVerifier.create(result)
            .expectNext(testConferenceDto)
            .expectComplete() // Verify the completion of the Flux
            .verify();
        verify(webClientMock).get();
        verify(cfbConfigMock).getBaseUrl();
        verify(requestHeadersUriSpecMock).uri("https://test/conferences");
        verify(requestHeadersSpecMock).header("Authorization", "Bearer api-key");
        verify(requestHeadersSpecMock).retrieve();
        verify(responseSpecMock).bodyToFlux(ConferenceDto.class);
        verify(cfbConfigMock).getApiKey();
        verify(conferenceService).getAllConferences();
    }

    private ConferenceDto getConferenceDto() {
        ConferenceDto conferenceDto = new ConferenceDto();
        conferenceDto.setId(1);
        conferenceDto.setName("Name");
        conferenceDto.setShort_name("Short Name");
        conferenceDto.setAbbreviation("Abbreviation");
        conferenceDto.setClassification("Classification");
        return conferenceDto;
    }

}
