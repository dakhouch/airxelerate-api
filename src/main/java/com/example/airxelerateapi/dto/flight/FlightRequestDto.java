package com.example.airxelerateapi.dto.flight;

import lombok.Builder;

@Builder
public record FlightRequestDto(String carrierCode,String flightNumber,String flightDate,String origin,String destination) {
}
