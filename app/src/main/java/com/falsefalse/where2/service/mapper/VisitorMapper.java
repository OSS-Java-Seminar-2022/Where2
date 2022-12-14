package com.falsefalse.where2.service.mapper;

import com.falsefalse.where2.models.Visitor;
import com.falsefalse.where2.persistence.entities.VisitorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VisitorMapper {

    VisitorMapper INSTANCE = Mappers.getMapper(VisitorMapper.class);

    Visitor fromEntity(VisitorEntity visitorEntity);

    VisitorEntity toEntity(Visitor visitor);

    @Mapping(target = "id", ignore = true)
    void updateEntity(Visitor visitor, @MappingTarget VisitorEntity visitorEntity);
}
