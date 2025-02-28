package com.example.airxelerateapi.api;

import com.example.airxelerateapi.dto.flight.FlightRequestDto;
import com.example.airxelerateapi.dto.flight.FlightResponseDto;
import com.example.airxelerateapi.dto.response.PageResponseDto;
import com.example.airxelerateapi.dto.response.Result;
import com.example.airxelerateapi.exception.BusinessException;
import com.example.airxelerateapi.service.facade.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public Result<Void> addFlight(@RequestBody FlightRequestDto flightRequestDto) throws BusinessException {
        return flightService.addFlight(flightRequestDto);
    }
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/{id}")
    public Result<FlightResponseDto> getFlightById(@PathVariable Long id) throws BusinessException {
        return flightService.getFlightById(id);
    }
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping
    public Result<PageResponseDto<FlightResponseDto>> getAllFlights(@RequestParam(defaultValue = "0") Integer page,
                                                                    @RequestParam(defaultValue = "10")Integer pageSize) throws BusinessException {
        return flightService.getAllFlights(PageRequest.of(page,pageSize));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public Result<Void> deleteFlight(@PathVariable Long id) throws BusinessException {
        return flightService.deleteFlight(id);
    }
}