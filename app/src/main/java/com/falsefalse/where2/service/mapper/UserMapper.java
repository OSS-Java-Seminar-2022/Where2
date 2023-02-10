package com.falsefalse.where2.service.mapper;

import com.falsefalse.where2.dto.RegistrationDto;
import com.falsefalse.where2.models.UserModel;
import com.falsefalse.where2.persistence.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserModel fromEntity(UserEntity userEntity);

    UserEntity toEntity(UserModel userModel);

    UserEntity toEntity(RegistrationDto userModel);

    @Mapping(target = "id", ignore = true)
    void updateEntity(UserModel userModel, @MappingTarget UserEntity userEntity);
}
