package com.example.airxelerateapi.service.impl;
import com.example.airxelerateapi.dto.auth.LoginRequestDto;
import com.example.airxelerateapi.entity.User;
import com.example.airxelerateapi.exceptions.BusinessException;
import com.example.airxelerateapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public User authenticate(LoginRequestDto input) throws BusinessException{
        log.debug(String.format("starting authentication service [email,password]=[%s,%s]",input.email(),input.password()));
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.email(),
                        input.password()
                )
        );
        return userRepository
                .findByEmail(input.email())
                .orElseThrow();
    }
}
