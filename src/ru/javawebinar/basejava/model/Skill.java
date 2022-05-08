package ru.javawebinar.basejava.model;

public class Skill {
    private String date;
    private String name;
    private String position;
    private String description;

    public Skill (String date, String name, String description) {
        this.date = date;
        this.name = name;
        this.description = description;
    }

    public Skill (String date, String name, String position, String description) {
        this.date = date;
        this.name = name;
        this.position = position;
        this.description = description;
    }

    public String getDate() {
        return date;
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
        return "Date " + date + "\n" +
                "Name " + name + "\n" +
                "Position " + position + "\n" +
                "Description '" + description;
    }
}
