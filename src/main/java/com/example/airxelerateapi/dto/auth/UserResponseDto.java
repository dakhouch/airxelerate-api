package com.example.airxelerateapi.dto.auth;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
class UserResponseDto{
    Long id;
    String username;
    String email;
}
