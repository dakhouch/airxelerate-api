package com.example.airxelerateapi.api;

import com.example.airxelerateapi.dto.auth.AccessTokenResponseDto;
import com.example.airxelerateapi.dto.response.Result;
import com.example.airxelerateapi.dto.auth.LoginRequestDto;
import com.example.airxelerateapi.dto.auth.AuthResponseDto;
import com.example.airxelerateapi.exception.BusinessException;
import com.example.airxelerateapi.service.impl.AuthenticationServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthenticationServiceImpl authenticationService;

    @PostMapping("/login")
    public Result<AuthResponseDto> authenticate(@RequestBody LoginRequestDto loginUserDto) throws BusinessException {
        return authenticationService.authenticate(loginUserDto);
    }
    @PostMapping("/refresh-token")
    public Result<AccessTokenResponseDto> refreshToken(@RequestBody String refreshToken) throws BusinessException {
        return authenticationService.authenticate(refreshToken);
    }
}
