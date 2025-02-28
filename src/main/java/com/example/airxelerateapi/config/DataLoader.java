package com.example.airxelerateapi.config;

import com.example.airxelerateapi.entity.User;
import com.example.airxelerateapi.enumeration.Role;
import com.example.airxelerateapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepository;

    @Override
    public void run(String... args) {
        userRepository.save(new User("Anass","Dakhouch", Role.USER,"anass.dakhouch@example.com","$2a$12$u8tcaGI8THHpLPLxwke7I.Dxe.ztBg.pcLIr.sXbLbyPjlcMhADci"));
    }
}