package ru.javawebinar.basejava.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Date {
    public static final LocalDate NOW = LocalDate.of(3000, 1, 1);

    public static LocalDate parse(String str) {
        if (str.equals("сейчас")) {
            return NOW;
        }
        LocalDate result;
        try {
            result = LocalDate.parse(str);
        } catch(DateTimeParseException e) {
            result = NOW;
        }
        return result;
    }
}
