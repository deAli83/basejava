package ru.javawebinar.basejava.model;

import java.io.Serializable;
import java.util.List;

public class Experience implements Serializable {
    private static final long serialVersionUID = 1L;

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
