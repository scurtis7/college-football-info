package com.scurtis.server.controller;

import com.scurtis.server.model.GameStatsDto;
import com.scurtis.server.service.GameStatsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "stats")
public class StatsController {

    private final GameStatsService gameStatsService;

    @GetMapping("game")
    public Flux<GameStatsDto> getStatsByGame(@RequestParam int year, @RequestParam String team, @RequestParam int week) {
        return gameStatsService.getGameStats(year, team, week);
    }

}
