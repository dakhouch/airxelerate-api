package com.example.airxelerateapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AirxelerateApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirxelerateApiApplication.class, args);
    }

}
