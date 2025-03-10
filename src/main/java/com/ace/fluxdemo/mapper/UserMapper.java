package com.ace.fluxdemo.mapper;

import com.ace.fluxdemo.entity.User;
import com.ace.fluxdemo.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponse toResponse(User userEntity);
}
