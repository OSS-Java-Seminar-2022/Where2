package com.falsefalse.where2.persistence.repositories;

import com.falsefalse.where2.persistence.entities.UserVenueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserVenueRepository extends JpaRepository<UserVenueEntity, Integer> {
    Boolean existsByUserIdAndVenueId(Integer userId, Integer venueId);

    List<UserVenueEntity> deleteAllByVenueId(Integer venueId);

    List<UserVenueEntity> deleteAllByVenueIdAndUserId(Integer venueId, Integer userId);

    List<UserVenueEntity> getAllByUserId(Integer userId);

    List<UserVenueEntity> getAllByVenueId(Integer venueId);
}
