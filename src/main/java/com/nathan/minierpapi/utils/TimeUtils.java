package com.nathan.minierpapi.utils;

import java.time.Instant;

public class TimeUtils {
    public Instant convertToInstant(String time) {
        return Instant.parse(
                time.substring(0,10) + "T"+time.substring(11,23) + "Z"
        );
    }
}
