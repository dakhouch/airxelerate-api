package com.example.airxelerateapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "flights")
@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Flight extends AbstractEntity {

    @Column(length = 2)
    private String carrierCode;

    @Column(length = 4, nullable = false)
    private String flightNumber;

    @Column(nullable = false)
    private Instant flightDate;

    @Column(length = 3, nullable = false)
    private String origin;

    @Column(length = 3, nullable = false)
    private String destination;

}