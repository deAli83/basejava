package ru.javawebinar.basejava.model;

import java.time.LocalDate;

public class Experience {
    private LocalDate startDate;
    private LocalDate finishDate;
    private String name;
    private String position;
    private String description;

    public Experience(LocalDate startDate, LocalDate finishDate, String name, String description) {
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.name = name;
        this.description = description;
    }

    public Experience(LocalDate startDate, LocalDate finishDate, String name, String position, String description) {
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.name = name;
        this.position = position;
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getFinishDate() {
        return startDate;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Дата: " + startDate + " - " + finishDate + "\n" +
                "Наименование: " + name + "\n" +
                "Позиция: " + position + "\n" +
                "Описание: '" + description;
    }
}
