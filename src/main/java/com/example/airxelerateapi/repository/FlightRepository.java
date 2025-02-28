package com.example.airxelerateapi.repository;

import com.example.airxelerateapi.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface FlightRepository extends JpaRepository<Flight,Long> ,QueryByExampleExecutor<Flight> {

}
