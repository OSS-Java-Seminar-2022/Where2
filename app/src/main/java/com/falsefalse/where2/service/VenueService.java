package com.falsefalse.where2.service;

import com.falsefalse.where2.models.VenueModel;
import com.falsefalse.where2.persistence.repositories.VenueRepository;
import com.falsefalse.where2.service.mapper.UserMapper;
import com.falsefalse.where2.service.mapper.VenueMapper;
import com.falsefalse.where2.utils.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class VenueService {
    private final static String NO_SUCH_ELEMENT_EXCEPTION_MESSAGE = "NoSuchElementException: Venue with [id: %s] not found!";

    private final VenueRepository venueRepository;
    private final CurrentUser currentUser;

    private final UserVenueService userVenueService;

    public List<VenueModel> getAll() {
        return venueRepository.findAll().stream().map(VenueMapper.INSTANCE::fromEntity).toList();
    }

    public VenueModel get(@PathVariable Integer id) {
        return VenueMapper.INSTANCE.fromEntity(venueRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format(NO_SUCH_ELEMENT_EXCEPTION_MESSAGE, id))));
    }

    public VenueModel create(VenueModel newVenueModel) {
        var user = currentUser.get();
        newVenueModel.setOwner(UserMapper.INSTANCE.fromEntity(user));
        var created = venueRepository.save(VenueMapper.INSTANCE.toEntity(newVenueModel));
        return VenueMapper.INSTANCE.fromEntity(created);
    }

    public VenueModel update(Integer id, VenueModel newVenueModel) {
        var user = currentUser.get();
        newVenueModel.setOwner(UserMapper.INSTANCE.fromEntity(user));

        var oldVenue = venueRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format(NO_SUCH_ELEMENT_EXCEPTION_MESSAGE, id)));

        VenueMapper.INSTANCE.updateEntity(newVenueModel, oldVenue);

        return VenueMapper.INSTANCE.fromEntity(venueRepository.save(oldVenue));
    }

    public HttpStatus delete(Integer id) {
        if (venueRepository.existsById(id)) {
            userVenueService.deleteAllByVenueId(id);
            venueRepository.deleteById(id);
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_FOUND;

    }
}
