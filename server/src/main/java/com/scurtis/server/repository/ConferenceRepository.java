package com.scurtis.server.repository;

import com.scurtis.server.entity.Conference;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ConferenceRepository extends ReactiveCrudRepository<Conference, Integer> {
}
