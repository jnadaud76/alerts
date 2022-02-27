package com.safetynet.alerts.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
@Component
public class Calculator {
    /**
     * @see Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Calculator.class);

    /**
     * Calculate a person's age from their birthDate.
     *
     * @param birthDate the birthdate of the person.
     * @return the age of the person.
     */
    public long calculateAge(final String birthDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate start = null;
        LocalDate end = null;

        try {
            start = LocalDate.parse(birthDate, formatter);
            end = LocalDate.now(ZoneId.systemDefault());
        } catch (DateTimeParseException e) {
            LOGGER.error("Bad date format provided", e);
        }

        if (start != null && end != null && start.isBefore(end)) {
            LOGGER.debug("Person age successfully calculated - value = {}",
                    ChronoUnit.YEARS.between(start, end));
            return ChronoUnit.YEARS.between(start, end);
        } else {
            LOGGER.error("Invalid start date", new IllegalArgumentException());
            throw new IllegalArgumentException();
        }

    }
}
