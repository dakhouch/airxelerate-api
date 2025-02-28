package com.example.airxelerateapi.util;

import com.example.airxelerateapi.dto.flight.FlightRequestDto;
import com.example.airxelerateapi.dto.flight.FlightResponseDto;
import com.example.airxelerateapi.entity.Flight;

import java.util.ArrayList;
import java.util.List;

public class FlightTestUtil {

    private FlightTestUtil() {
        throw new IllegalStateException("FlightTestUtil private constructor");
    }

    public static FlightRequestDto generateRandomFlightRequestDto() {
       return   FlightRequestDto.builder()
                .flightDate(TestUtil.generateRandomFlightDate().toString())
                .flightNumber(TestUtil.generateRandomFlightNumber())
                .origin(TestUtil.generateRandomAirportCode())
                .destination(TestUtil.generateRandomAirportCode())
                .carrierCode(TestUtil.generateRandomAirlineCode())
                .build();

    }
    public static FlightResponseDto generateRandomFlightResponseDto() {
        return   FlightResponseDto.builder()
                .flightDate(TestUtil.generateRandomFlightDate().toString())
                .flightNumber(TestUtil.generateRandomFlightNumber())
                .origin(TestUtil.generateRandomAirportCode())
                .destination(TestUtil.generateRandomAirportCode())
                .carrierCode(TestUtil.generateRandomAirlineCode())
                .id(TestUtil.generateRandomId())
                .build();

    }

    public static Flight generateRandomFlightEntity() {
        Flight flightEntity = new Flight();
        flightEntity.setFlightNumber(TestUtil.generateRandomFlightNumber());
        flightEntity.setOrigin(TestUtil.generateRandomAirportCode());
        flightEntity.setDestination(TestUtil.generateRandomAirportCode());
        flightEntity.setFlightDate(TestUtil.generateRandomFlightDate());
        flightEntity.setCarrierCode(TestUtil.generateRandomAirlineCode());
        return flightEntity;
    }
    public static List<Flight> generateRandomFlightEntityList() {
        return new ArrayList<>(List.of(generateRandomFlightEntity(),generateRandomFlightEntity(),generateRandomFlightEntity()));
    }
}