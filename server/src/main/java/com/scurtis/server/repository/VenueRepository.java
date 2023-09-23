package com.scurtis.server.repository;

import com.scurtis.server.entity.Venue;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface VenueRepository extends ReactiveCrudRepository<Venue, Integer> {
}
