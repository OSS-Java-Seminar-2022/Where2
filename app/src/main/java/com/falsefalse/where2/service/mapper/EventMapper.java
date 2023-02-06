package com.falsefalse.where2.service.mapper;

import com.falsefalse.where2.models.EventModel;
import com.falsefalse.where2.persistence.entities.EventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EventMapper {

    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    EventModel fromEntity(EventEntity eventEntity);

    EventEntity toEntity(EventModel eventModel);

    @Mapping(target = "id", ignore = true)
    void updateEntity(EventModel eventModel, @MappingTarget EventEntity eventEntity);
}

