package com.falsefalse.where2.persistence.repositories;

import com.falsefalse.where2.persistence.entities.VenueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueRepository extends JpaRepository<VenueEntity, Integer> {
}
