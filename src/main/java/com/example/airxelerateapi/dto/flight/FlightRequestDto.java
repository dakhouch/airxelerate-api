package com.example.airxelerateapi.dto.flight;

public record FlightRequestDto(String carrierCode,String flightNumber,String flightDate,String origin,String destination) {
}
