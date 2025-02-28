package com.example.airxelerateapi.service.impl;
import com.example.airxelerateapi.config.jwt.JWTProvider;
import com.example.airxelerateapi.dto.auth.AccessTokenResponseDto;
import com.example.airxelerateapi.dto.auth.LoginRequestDto;
import com.example.airxelerateapi.dto.auth.AuthResponseDto;
import com.example.airxelerateapi.dto.response.MessageResult;
import com.example.airxelerateapi.dto.response.Result;
import com.example.airxelerateapi.entity.User;
import com.example.airxelerateapi.enumeration.MessageStatus;
import com.example.airxelerateapi.exception.BusinessException;
import com.example.airxelerateapi.exception.TechnicalException;
import com.example.airxelerateapi.mapper.AuthMapper;
import com.example.airxelerateapi.repository.UserRepository;
import com.example.airxelerateapi.service.facade.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import static com.example.airxelerateapi.util.Constants.AUTH_TOKEN_TYPE;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    private final JWTProvider jwtProvider;
    private final AuthMapper authMapper;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public Result<AuthResponseDto> authenticate(LoginRequestDto input) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.email(),
                        input.password()
                )
        );

        User authenticatedUser=userRepository
                .findByEmail(input.email())
                .orElseThrow(()->new TechnicalException("CONFLICT"));

        AccessTokenResponseDto accessTokenResponseDto=AccessTokenResponseDto
                .builder()
                .token(jwtProvider.generateAccessToken(authenticatedUser))
                .expiresIn(jwtProvider.getRefreshTokenExpirationTime())
                .build();

        String refreshToken = jwtProvider.generateRefreshToken(authenticatedUser);

        AuthResponseDto loginResponse =  AuthResponseDto
                .builder()
                .tokenType(AUTH_TOKEN_TYPE)
                .accessToken(accessTokenResponseDto)
                .refreshToken(refreshToken)
                .user(authMapper.toUserResponseDto(authenticatedUser))
                .build();

        return  Result.createResultWithBody(
                HttpStatus.OK,
                MessageResult.getMessageResultWithoutField("AUTH_SUCCESS", MessageStatus.INFO),
                loginResponse
        );
    }
    @Override
    public Result<AccessTokenResponseDto> authenticate(String refreshToken) throws BusinessException {
        String username = jwtProvider.extractUsername(refreshToken);
        User authenticatedUser = userRepository
                .findByEmail(username)
                .orElseThrow(() -> new TechnicalException("CONFLICT"));

        if (!jwtProvider.isTokenValid(refreshToken, authenticatedUser)) throw new BusinessException("INVALID_REFRESH_TOKEN",HttpStatus.UNAUTHORIZED);


        AccessTokenResponseDto accessTokenResponseDto = AccessTokenResponseDto
                .builder()
                .token(jwtProvider.generateAccessToken(authenticatedUser))
                .expiresIn(jwtProvider.getAccessTokenExpirationTime())
                .build();

        return Result.createResultWithBody(
                HttpStatus.OK,
                MessageResult.getMessageResultWithoutField("REFRESH_TOKEN_SUCCESS", MessageStatus.INFO),
                accessTokenResponseDto
        );
    }
}

