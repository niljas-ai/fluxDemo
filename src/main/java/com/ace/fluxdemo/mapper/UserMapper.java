package com.ace.fluxdemo.mapper;

import com.ace.fluxdemo.entity.User;
import com.ace.fluxdemo.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    UserResponse toResponse(User userEntity);
}
