package ru.javawebinar.basejava.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Period implements Serializable {
    private static final long serialVersionUID = 1L;

    private LocalDate startDate;
    private LocalDate finishDate;
    private String position;
    private String description;

    public Period(LocalDate startDate, LocalDate finishDate, String description) {
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.description = description;
    }

    public Period(LocalDate startDate, LocalDate finishDate, String position, String description) {
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.position = position;
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getFinishDate() {
        return startDate;
    }

    public String getPosition() {
        return position;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        if (position != null) {
            position = "Позиция: " + position + "\n";
        } else {
            position = "";
        }
        return ("\n" + "Дата: " + startDate + " - " + finishDate + "\n" +
                position +
                "Описание: '" + description);
    }
}
