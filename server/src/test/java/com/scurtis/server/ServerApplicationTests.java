package com.scurtis.server;

import com.scurtis.server.service.ConferenceService;
import com.scurtis.server.service.TeamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
class ServerApplicationTests {

    @Autowired
    private ConferenceService conferenceService;

    @Autowired
    private TeamService teamService;

    @Test
    void contextLoads() {
        assertNotNull(conferenceService);
        assertNotNull(teamService);
    }

}
