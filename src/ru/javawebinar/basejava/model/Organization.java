package ru.javawebinar.basejava.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Organization extends AbstractSection {
    private static final long serialVersionUID = 1L;

    private List<Experience> experience;

    public Organization() {}

    public Organization(Experience... experiences) {
        this(Arrays.asList(experiences));
    }
    public Organization(String name, String link, List<Period> periods) {}

    public Organization(List<Experience> experience) {
        this.experience = experience;
    }

    public List<Experience> getOrganizations() {
        return experience;
    }

    @Override
    public String toString() {
        return experience.toString().replaceAll("[\\[|\\]]", "");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(experience, that.experience);
    }

    @Override
    public int hashCode() {
        return Objects.hash(experience);
    }
}
