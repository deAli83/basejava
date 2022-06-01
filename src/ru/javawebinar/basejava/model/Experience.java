package ru.javawebinar.basejava.model;

import java.util.List;

public class Experience {
    private String name;
    private String link;
    private List<Period> periods;

    public Experience(String name, String link, List<Period> periods) {
        this.name = name;
        this.link = link;
        this.periods = periods;
    }

    @Override
    public String toString() {
        return ("\n\n" + "Наименование: " + name + link + periods);
    }
}
