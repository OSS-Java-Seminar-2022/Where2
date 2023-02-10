package com.falsefalse.where2.service;

import com.falsefalse.where2.persistence.entities.UserVenueEntity;
import com.falsefalse.where2.persistence.repositories.UserVenueRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserVenueService {
    private final UserVenueRepository userVenueRepository;

    public HttpStatus subscribe(Integer venueId, Integer userId) {
        if (userVenueRepository.existsByUserIdAndVenueId(userId, venueId)) return HttpStatus.ALREADY_REPORTED;

        userVenueRepository.save(UserVenueEntity.builder().userId(userId).venueId(venueId).build());
        return HttpStatus.OK;
    }

    @Transactional
    public HttpStatus unsubscribe(Integer venueId, Integer userId) {
        if (userVenueRepository.existsByUserIdAndVenueId(userId, venueId))
            userVenueRepository.deleteAllByVenueIdAndUserId(venueId, userId);
        return HttpStatus.OK;
    }

    public List<UserVenueEntity> getAllByUserId(Integer userId) {
        return userVenueRepository.getAllByUserId(userId);
    }

    public List<UserVenueEntity> getAllByEventId(Integer venueId) {
        return userVenueRepository.getAllByVenueId(venueId);
    }

    @Transactional
    public void deleteAllByVenueId(Integer id) {
        userVenueRepository.deleteAllByVenueId(id);
    }
}
