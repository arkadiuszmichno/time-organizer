package com.michno.organizer.util;

import java.time.Instant;
import java.time.ZonedDateTime;

public class InstantMapper {

    public static Instant mapStringToInstant(String date) {

        if (date != null && date != "") {
            Instant result = ZonedDateTime.parse(date).toInstant();
            return result;
        }
        return null;
    }

    public static String mapInstantToString(Instant date) {
        if (date != null) {
            String result = date.toString();
            return result;
        }
        return null;
    }
}
