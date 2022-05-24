package ru.javawebinar.basejava.model;

import java.util.List;

public class Experience {
    private String name;
    private List<Period> periods;

    public Experience(String name, List<Period> periods) {
        this.name = name;
        this.periods = periods;
    }

    @Override
    public String toString() {
        return ("\n\n" + "Наименование: " + name + periods);
    }
}
