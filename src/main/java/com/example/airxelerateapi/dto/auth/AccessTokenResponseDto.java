package com.example.airxelerateapi.dto.auth;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(builderClassName = "Builder", toBuilder = true)
public class AccessTokenResponseDto {
    String token;
    Long expiresIn;
}
