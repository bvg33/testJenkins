package com.epam.ems.logic.handler;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static java.time.LocalDateTime.now;
import static java.time.format.DateTimeFormatter.ofPattern;

@Component
public class DateHandler {
    public String getCurrentDate() {
        LocalDateTime localDateTime = now();
        DateTimeFormatter formatter = ofPattern("yyyy-MM-dd'T'HH:mm", Locale.UK);
        return formatter.format(localDateTime);
    }
}
