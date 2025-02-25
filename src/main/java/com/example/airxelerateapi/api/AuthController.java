package com.example.airxelerateapi.api;

import com.example.airxelerateapi.config.jwt.JWTProvider;
import com.example.airxelerateapi.dto.MessageResult;
import com.example.airxelerateapi.dto.Result;
import com.example.airxelerateapi.dto.auth.LoginRequestDto;
import com.example.airxelerateapi.dto.auth.LoginResponseDto;
import com.example.airxelerateapi.entity.User;
import com.example.airxelerateapi.enumeration.MessageStatus;
import com.example.airxelerateapi.enumeration.ResultStatus;
import com.example.airxelerateapi.exceptions.BusinessException;
import com.example.airxelerateapi.service.impl.AuthenticationServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final JWTProvider jwtProvider;
    private final AuthenticationServiceImpl authenticationService;

    @PostMapping("/login")
    public Result<LoginResponseDto> authenticate(@RequestBody LoginRequestDto loginUserDto) throws BusinessException {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtProvider.generateToken(authenticatedUser);
        LoginResponseDto loginResponse =  LoginResponseDto
               .builder()
               .accessToken(jwtToken)
               .expiresIn(jwtProvider.getExpirationTime())
               .build();
        return Result.createResultWithBody(
                ResultStatus.SUCCESS,
                MessageResult.getMessageResultWithoutField("AUTH_SUCCESS", MessageStatus.INFO),
                loginResponse
        );
    }
}
