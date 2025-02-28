package com.example.airxelerateapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.time.Instant;

@Mapper
public interface DateMapper {

    @Named("stringToInstant")
    static Instant stringToInstant(String date) {
        return (date != null && !date.isEmpty()) ? Instant.parse(date) : null;
    }

    @Named("instantToString")
    static String instantToString(Instant date) {
        return (date != null) ? date.toString() : null;
    }
}
