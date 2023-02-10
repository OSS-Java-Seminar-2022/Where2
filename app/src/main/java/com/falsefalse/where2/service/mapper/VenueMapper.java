package com.falsefalse.where2.service.mapper;

import com.falsefalse.where2.models.VenueModel;
import com.falsefalse.where2.persistence.entities.VenueEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VenueMapper {

    VenueMapper INSTANCE = Mappers.getMapper(VenueMapper.class);
    
    VenueModel fromEntity(VenueEntity venueEntity);

    VenueEntity toEntity(VenueModel venueModel);

    @Mapping(target = "id", ignore = true)
    void updateEntity(VenueModel venueModel, @MappingTarget VenueEntity venueEntity);
}

