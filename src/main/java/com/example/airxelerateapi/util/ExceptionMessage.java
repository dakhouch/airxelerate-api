package com.example.airxelerateapi.util;


public interface ExceptionMessage {
    String INVALID_FIELDS = "exception.InvalidFields";
    String INVALID_FIELD = "exception.InvalidField";


    String FLIGHT_ALREADY_EXIST="exception.FlightAlreadyExistException";
    String FLIGHT_NOT_FOUND="exception.FlightNotFoundException";

    String INVALID_REFRESH_TOKEN="exception.InvalidRefreshTokenException";
    String UNEXPECTED_BEHAVIOR="exception.UnexpectedBehaviorException";
}