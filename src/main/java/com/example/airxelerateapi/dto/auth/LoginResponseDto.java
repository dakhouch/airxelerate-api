package com.example.airxelerateapi.dto.auth;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(builderClassName = "Builder", toBuilder = true)
public class LoginResponseDto {
    String accessToken;
    String refreshToken;
    String tokenType;
    Long expiresIn;
}

