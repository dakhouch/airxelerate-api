package com.example.airxelerateapi.service.impl;

import com.example.airxelerateapi.dto.flight.FlightRequestDto;
import com.example.airxelerateapi.dto.flight.FlightResponseDto;
import com.example.airxelerateapi.dto.response.MessageResult;
import com.example.airxelerateapi.dto.response.PageResponseDto;
import com.example.airxelerateapi.dto.response.Pagination;
import com.example.airxelerateapi.dto.response.Result;
import com.example.airxelerateapi.entity.Flight;
import com.example.airxelerateapi.enumeration.MessageStatus;
import com.example.airxelerateapi.exception.BusinessException;
import com.example.airxelerateapi.mapper.FlightMapper;
import com.example.airxelerateapi.repository.FlightRepository;
import com.example.airxelerateapi.service.core.MessageReader;
import com.example.airxelerateapi.service.facade.FlightService;
import com.example.airxelerateapi.util.ApiMessage;
import com.example.airxelerateapi.util.ExceptionMessage;
import com.example.airxelerateapi.validator.FlightValidator;
import com.example.airxelerateapi.validator.GlobalValidator;
import lombok.RequiredArgsConstructor;
import org.aspectj.bridge.MessageWriter;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;
    private final MessageReader messageReader;
    private final FlightValidator flightValidator;
    private final GlobalValidator globalValidator;

    @Override
    public Result<Void> addFlight(FlightRequestDto flightRequestDto) throws BusinessException {
        flightValidator.validate(flightRequestDto);
        Flight flight=flightMapper.toFlight(flightRequestDto);
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
        Example<Flight> example= Example.of(flightMapper.toFlight(flightRequestDto),matcher);
        if(flightRepository.findOne(example).isPresent()) throw new BusinessException(messageReader.getMessage(ExceptionMessage.FLIGHT_ALREADY_EXIST),HttpStatus.CONFLICT);
        flightRepository.save(flight);
        return Result.createResultWithoutBody(
                HttpStatus.OK,
                new MessageResult(messageReader.getMessage(ApiMessage.CREATE_FLIGHT),MessageStatus.INFO));
    }

    @Override
    public Result<FlightResponseDto> getFlightById(Long id) throws BusinessException {
        Flight flight=flightRepository.findById(id).orElseThrow(()->new BusinessException(ExceptionMessage.FLIGHT_NOT_FOUND,HttpStatus.NOT_FOUND));
        FlightResponseDto flightResponseDto=flightMapper.toFlightResponseDto(flight);
        return Result.createResultWithBody(
                HttpStatus.OK,
                new MessageResult(ApiMessage.GET_FLIGHT_BY_ID,MessageStatus.INFO),
                flightResponseDto);
    }

    @Override
    public Result<PageResponseDto<FlightResponseDto>> getAllFlights(Pageable pageable) throws BusinessException {
       globalValidator.validatePageRequest(pageable.getPageNumber(),pageable.getPageSize());
       Page<Flight> flights=flightRepository.findAll(pageable);
       Pagination pagination= Pagination
               .builder()
               .currentPage(flights.getPageable().getPageNumber())
               .totalItems(flights.getTotalElements())
               .totalPages(flights.getTotalPages())
               .build();
       List<FlightResponseDto> flightResponseDtoList=flights
               .map(flightMapper::toFlightResponseDto)
               .getContent();

       return Result.createResultWithBody(
                HttpStatus.OK,
                new MessageResult(messageReader.getMessage(ApiMessage.GET_FLIGHTS),MessageStatus.INFO),
                new PageResponseDto<>(pagination,flightResponseDtoList)
        );
    }

    @Override
    public Result<Void> deleteFlight(Long id) throws BusinessException {
        flightRepository.findById(id).orElseThrow(()->new BusinessException(ExceptionMessage.FLIGHT_NOT_FOUND,HttpStatus.NOT_FOUND));
        flightRepository.deleteById(id);
        return Result.createResultWithoutBody(
                HttpStatus.OK,
                new MessageResult(messageReader.getMessage(ApiMessage.DELETE_FLIGHT),MessageStatus.INFO));
    }
}
