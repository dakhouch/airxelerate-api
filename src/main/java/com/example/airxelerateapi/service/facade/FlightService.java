package com.example.airxelerateapi.service.facade;

import com.example.airxelerateapi.dto.flight.FlightRequestDto;
import com.example.airxelerateapi.dto.flight.FlightResponseDto;
import com.example.airxelerateapi.dto.response.PageResponseDto;
import com.example.airxelerateapi.dto.response.Result;
import com.example.airxelerateapi.exception.BusinessException;
import org.springframework.data.domain.Pageable;


public interface FlightService  {
    Result<Void> addFlight(FlightRequestDto flight) throws BusinessException;

    Result<FlightResponseDto> getFlightById(Long id) throws BusinessException;

    Result<PageResponseDto<FlightResponseDto>> getAllFlights(Integer page,Integer pageSize) throws BusinessException;

    Result<Void> deleteFlight(Long id) throws BusinessException;
}
