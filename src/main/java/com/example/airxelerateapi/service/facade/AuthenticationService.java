package com.example.airxelerateapi.service.facade;

import com.example.airxelerateapi.dto.auth.AccessTokenResponseDto;
import com.example.airxelerateapi.dto.auth.LoginRequestDto;
import com.example.airxelerateapi.dto.auth.AuthResponseDto;
import com.example.airxelerateapi.dto.response.Result;
import com.example.airxelerateapi.exception.BusinessException;


public interface AuthenticationService {
     Result<AuthResponseDto> authenticate(LoginRequestDto input) throws BusinessException;
     Result<AccessTokenResponseDto> authenticate(String refreshToken) throws BusinessException;
}
