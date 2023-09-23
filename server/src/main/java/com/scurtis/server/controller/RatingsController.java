package com.scurtis.server.controller;

import com.scurtis.server.model.FpiDto;
import com.scurtis.server.model.SpDto;
import com.scurtis.server.service.FpiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "ratings")
public class RatingsController {

    private final FpiService fpiService;

    @GetMapping(path = "fpi/year/{year}")
    public Flux<FpiDto> getFpiByYear(@PathVariable int year) {
        return fpiService.getFpiByYear(year);
    }

    @GetMapping(path = "sp/year/{year}")
    public Flux<SpDto> getSpByYear(@PathVariable int year) {
        return fpiService.getSpByYear(year);
    }

}
