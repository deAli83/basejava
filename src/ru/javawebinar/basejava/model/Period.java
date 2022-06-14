package ru.javawebinar.basejava.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return Objects.equals(startDate, period.startDate) && Objects.equals(finishDate, period.finishDate) && Objects.equals(position, period.position) && Objects.equals(description, period.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, finishDate, position, description);
    }
}
