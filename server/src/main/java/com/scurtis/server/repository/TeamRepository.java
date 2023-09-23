package com.scurtis.server.repository;

import com.scurtis.server.entity.Team;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TeamRepository extends ReactiveCrudRepository<Team, Integer> {
}
