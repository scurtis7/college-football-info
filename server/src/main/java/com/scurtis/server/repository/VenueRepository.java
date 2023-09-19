package com.scurtis.server.repository;

import com.scurtis.server.entity.Venue;
import org.springframework.data.repository.CrudRepository;

public interface VenueRepository extends CrudRepository<Venue, Integer> {
}
