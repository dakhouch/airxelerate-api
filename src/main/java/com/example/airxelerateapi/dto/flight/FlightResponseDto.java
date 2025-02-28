package com.example.airxelerateapi.dto.flight;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
public class FlightResponseDto {
    private Long id;
    private String carrierCode;
    private String flightNumber;
    private String flightDate;// ISO 8601
    private String origin;
    private String destination;
}
