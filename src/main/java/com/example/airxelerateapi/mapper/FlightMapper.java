package com.example.airxelerateapi.mapper;
import com.example.airxelerateapi.dto.flight.FlightRequestDto;
import com.example.airxelerateapi.dto.flight.FlightResponseDto;
import com.example.airxelerateapi.entity.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = DateMapper.class)
public interface FlightMapper {
    @Mapping(target = "flightDate", source = "flightDate", qualifiedByName = "instantToString")
    FlightResponseDto toFlightResponseDto(Flight flight);

    @Mapping(target = "flightDate", source = "flightDate", qualifiedByName = "stringToInstant")
    Flight toFlight(FlightRequestDto flightRequestDto);
}
