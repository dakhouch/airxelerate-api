package com.example.airxelerateapi.util;

public interface ApiMessage {
    String CREATE_FLIGHT = "flightApi.createFlight";
    String DELETE_FLIGHT = "flightApi.deleteFlight";
    String GET_FLIGHT_BY_ID = "flightApi.getFlightById";
    String GET_FLIGHTS = "flightApi.getAllFlights";

    String GET_REFRESH_TOKEN="authApi.getRefreshToken";
    String POST_AUTH_USER="authApi.postAuthUser";
}
