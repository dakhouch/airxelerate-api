package com.example.airxelerateapi.validator;

import com.example.airxelerateapi.dto.flight.FlightRequestDto;
import com.example.airxelerateapi.dto.response.MessageResult;
import com.example.airxelerateapi.enumeration.MessageStatus;
import com.example.airxelerateapi.exception.BusinessException;
import com.example.airxelerateapi.service.core.MessageReader;
import com.example.airxelerateapi.util.ExceptionMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FlightValidator {

    private static final String CARRIER_CODE = "carrierCode";
    private static final String FLIGHT_NUMBER = "flightNumber";
    private static final String ORIGIN = "origin";
    private static final String DESTINATION = "destination";

    private static final String CARRIER_CODE_REGEX = "^[A-Z]{2}$";
    private static final String FLIGHT_NUMBER_REGEX = "^\\d{4}$";
    private static final String AIRPORT_CODE_REGEX = "^[A-Z]{3}$";

    private final MessageReader messageReader;

    public void validate(FlightRequestDto flightRequest) throws BusinessException {
        List<MessageResult> messages = new ArrayList<>();

        validateCarrierCode(flightRequest.carrierCode(), messages);
        validateFlightNumber(flightRequest.flightNumber(), messages);
        validateAirportCode(flightRequest.origin(), ORIGIN, messages);
        validateAirportCode(flightRequest.destination(), DESTINATION, messages);
        validateFlightDate(flightRequest.flightDate(),messages);
        if (!messages.isEmpty()) {
            throw new BusinessException(
                    messageReader.getMessage(ExceptionMessage.INVALID_FIELDS),
                    messages,
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    private void validateCarrierCode(String carrierCode, List<MessageResult> messages) {
        if (carrierCode == null || !carrierCode.matches(CARRIER_CODE_REGEX)) {
            messages.add(new MessageResult(
                    messageReader.getMessage(ExceptionMessage.INVALID_FIELD, new String[]{CARRIER_CODE}),
                    MessageStatus.ERROR
            ));
        }
    }

    private void validateFlightNumber(String flightNumber, List<MessageResult> messages) {
        if (flightNumber == null || !flightNumber.matches(FLIGHT_NUMBER_REGEX)) {
            messages.add(new MessageResult(
                    messageReader.getMessage(ExceptionMessage.INVALID_FIELD, new String[]{FLIGHT_NUMBER}),
                    MessageStatus.ERROR
            ));
        }
    }

    private void validateAirportCode(String code, String fieldName, List<MessageResult> messages) {
        if (code == null || !code.matches(AIRPORT_CODE_REGEX)) {
            messages.add(new MessageResult(
                    messageReader.getMessage(ExceptionMessage.INVALID_FIELD, new String[]{fieldName}),
                    MessageStatus.ERROR
            ));
        }
    }
    private void validateFlightDate(String flightDate, List<MessageResult> messages) {
        if (flightDate == null || !Asserts.isValidISO8601(flightDate)) {
            messages.add(new MessageResult(
                    messageReader.getMessage(ExceptionMessage.INVALID_FIELD, new String[]{flightDate}),
                    MessageStatus.ERROR
            ));
        }
    }
}
