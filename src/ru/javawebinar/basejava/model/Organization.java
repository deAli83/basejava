package ru.javawebinar.basejava.model;

import java.util.List;

public class Organization extends AbstractSection {
    private List<Experience> experience;

    public Organization(List<Experience> experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return experience.toString().replaceAll("[\\[|\\]|,]", "");
    }
}
