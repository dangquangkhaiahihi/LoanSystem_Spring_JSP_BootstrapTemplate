package com.example.demo.common;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {
    public static Authentication getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth;
    }

    public static String convertLocalDateTimeToddMMyyy(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return localDateTime.format(formatter);
    }

    public static LocalDateTime convertyyyyMMddToLocalDateTime(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateString, formatter);
        LocalDateTime localDateTime = date.atStartOfDay();
        return localDateTime;
    }

    public static String convertLocalDateTimeToyyyyMMdd(LocalDateTime datetime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return datetime.format(formatter);
    }
}
