package ru.javawebinar.basejava.model;

import java.io.Serializable;
import java.util.List;

public class Organization extends AbstractSection implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Experience> experience;

    public Organization(List<Experience> experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return experience.toString().replaceAll("[\\[|\\]]", "");
    }
}
