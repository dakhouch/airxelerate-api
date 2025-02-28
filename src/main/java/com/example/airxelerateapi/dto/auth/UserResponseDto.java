package com.example.airxelerateapi.dto.auth;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserResponseDto{
    Long id;
    String fullName;
    String email;
}
