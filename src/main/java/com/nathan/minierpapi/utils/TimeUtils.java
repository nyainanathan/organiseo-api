package com.nathan.minierpapi.utils;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;

@Component
public class TimeUtils {
    public Instant convertToInstant(String time) {
        if (time == null) {
            return null;
        }
        return Instant.parse(
                time.substring(0,10) + "T"+time.substring(11,20) + "Z"
        );
    }

    public Timestamp  convertToTimestamp(String time) {
        if (time == null) {
            return null;
        }
        return Timestamp.from(Instant.parse(time));
    }
}
