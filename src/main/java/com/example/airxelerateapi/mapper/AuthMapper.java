package com.example.airxelerateapi.mapper;

import com.example.airxelerateapi.dto.auth.UserResponseDto;
import com.example.airxelerateapi.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    @Mapping(target = "fullName", source = "fullName")
    @Mapping(target = "email", source = "email")
    UserResponseDto toUserResponseDto(User user);

}

