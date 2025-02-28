package com.example.airxelerateapi.service.impl;

import com.example.airxelerateapi.dto.flight.FlightRequestDto;
import com.example.airxelerateapi.dto.flight.FlightResponseDto;
import com.example.airxelerateapi.dto.response.PageResponseDto;
import com.example.airxelerateapi.dto.response.Result;
import com.example.airxelerateapi.entity.Flight;
import com.example.airxelerateapi.exception.BusinessException;
import com.example.airxelerateapi.mapper.FlightMapper;
import com.example.airxelerateapi.repository.FlightRepository;
import com.example.airxelerateapi.service.core.MessageReader;
import com.example.airxelerateapi.util.ExceptionMessage;
import com.example.airxelerateapi.util.FlightTestUtil;
import com.example.airxelerateapi.util.TestUtil;
import com.example.airxelerateapi.validator.FlightValidator;
import com.example.airxelerateapi.validator.GlobalValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlightServiceTest {

    @InjectMocks
    FlightServiceImpl flightService;

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private FlightMapper flightMapper;

    @Mock
    private MessageReader messageReader;

    @Mock
    private FlightValidator flightValidator;

    @Mock
    private GlobalValidator globalValidator;

    @Test
    void shouldThrowExceptionWhenAddFlightAlreadyExists() throws BusinessException {
        // given
        FlightRequestDto flightRequestDto = FlightTestUtil.generateRandomFlightRequestDto();
        Flight flight = FlightTestUtil.generateRandomFlightEntity();
        when(flightMapper.toFlight(any(FlightRequestDto.class))).thenReturn(flight);
        when(flightRepository.findOne(any(Example.class))).thenReturn(Optional.of(flight));
        when(messageReader.getMessage(any(String.class))).thenReturn(ExceptionMessage.FLIGHT_ALREADY_EXIST);

        // when
        Executable executable = () -> flightService.addFlight(flightRequestDto);

        // then
        BusinessException businessException = Assertions.assertThrows(BusinessException.class, executable);
        String expectedMessage = messageReader.getMessage(ExceptionMessage.FLIGHT_ALREADY_EXIST);
        String actualMessage = businessException.getMessage();
        assertThat(expectedMessage).isEqualTo(actualMessage);
    }

    @Test
    void shouldReturnFlightWhenGetFlightById() throws BusinessException {
        // given
        Long id = 1L;
        Flight flight = FlightTestUtil.generateRandomFlightEntity();
        flight.setId(id);

        FlightResponseDto flightResponseDto = FlightTestUtil.generateRandomFlightResponseDto();
        flightResponseDto.setId(id);

        when(flightRepository.findById(id)).thenReturn(Optional.of(flight));
        when(flightMapper.toFlightResponseDto(flight)).thenReturn(flightResponseDto);

        // when
        Result<FlightResponseDto> result = flightService.getFlightById(id);

        // then
        assertThat(result.body().getId()).isEqualTo(id);
    }

    @Test
    void shouldReturnAllFlightsWithPagination() throws BusinessException {
        // given
        Integer page=TestUtil.getRandomInteger(0,60);
        Integer size=TestUtil.getRandomInteger(0,100);
        Long totalElements =TestUtil.getRandomLong(100,200);
        List<Flight> flightList = FlightTestUtil.generateRandomFlightEntityList();
        Page<Flight> flightPage = new PageImpl<>(flightList,PageRequest.of(page,size),totalElements);
        when(flightRepository.findAll(any(Pageable.class))).thenReturn(flightPage);
        doNothing().when(globalValidator).validatePageRequest(anyInt(), anyInt());

        // when
        Result<PageResponseDto<FlightResponseDto>> result = flightService.getAllFlights(page,size);

        // then
        assertThat(result.body().pagination().currentPage()).isEqualTo(page);

    }

    @Test
    void shouldThrowExceptionWhenDeleteFlightNotFound() throws BusinessException {
        // given
        Long id = 1L;
        when(flightRepository.findById(id)).thenReturn(Optional.empty());
        when(messageReader.getMessage(any(String.class))).thenReturn(ExceptionMessage.FLIGHT_NOT_FOUND);

        // when
        Executable executable = () -> flightService.deleteFlight(id);

        // then
        BusinessException businessException = Assertions.assertThrows(BusinessException.class, executable);
        String expectedMessage = messageReader.getMessage(ExceptionMessage.FLIGHT_NOT_FOUND);
        String actualMessage = businessException.getMessage();
        assertThat(expectedMessage).isEqualTo(actualMessage);
    }

    @Test
    void shouldInvokeDeleteMethodWhenDeleteExistingFlight() throws BusinessException {
        // given
        Long id = 1L;
        when(flightRepository.findById(id)).thenReturn(Optional.of(FlightTestUtil.generateRandomFlightEntity()));
        doNothing().when(flightRepository).deleteById(id);

        // when
        flightService.deleteFlight(id);

        // then
        verify(flightRepository, times(1)).deleteById(id);
    }

    @Test
    void shouldInvokeSaveMethodWhenAddNewFlight() throws BusinessException {
        // given
        FlightRequestDto flightRequestDto = FlightTestUtil.generateRandomFlightRequestDto();
        Flight flight = FlightTestUtil.generateRandomFlightEntity();
        when(flightMapper.toFlight(flightRequestDto)).thenReturn(flight);
        when(flightRepository.findOne(any(Example.class))).thenReturn(Optional.empty());
        when(flightRepository.save(any(Flight.class))).thenReturn(flight);
        doNothing().when(flightValidator).validate(flightRequestDto);

        // when
        flightService.addFlight(flightRequestDto);

        // then
        verify(flightRepository, times(1)).save(any(Flight.class));
    }
}
