package com.example.airxelerateapi.validator;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public interface Asserts {

    static boolean isValidISO8601(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
            TemporalAccessor temporal = formatter.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}