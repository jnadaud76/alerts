package com.safetynet.alerts.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Calculator {

    public long calculateAge(final String birthDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate start = LocalDate.parse(birthDate, formatter);
        LocalDate end = LocalDate.now(ZoneId.systemDefault());
        return ChronoUnit.YEARS.between(start, end);
    }
}
