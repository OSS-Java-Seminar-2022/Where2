package com.falsefalse.where2.service.mapper;

import com.falsefalse.where2.models.UserModel;
import com.falsefalse.where2.persistence.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserModel fromEntity(UserEntity userEntity);

    UserEntity toEntity(UserModel userModel);

    @Mapping(target = "id", ignore = true)
    void updateEntity(UserModel userModel, @MappingTarget UserEntity userEntity);
}
