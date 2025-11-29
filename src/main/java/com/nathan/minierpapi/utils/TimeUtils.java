package com.nathan.minierpapi.utils;

import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class TimeUtils {
    public Instant convertToInstant(String time) {
        if (time == null) {
            return null;
        }
        return Instant.parse(
                time.substring(0,10) + "T"+time.substring(11,23) + "Z"
        );
    }
}
