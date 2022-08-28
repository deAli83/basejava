package ru.javawebinar.basejava.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Experience implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String link;
    private List<Period> periods;

    public static final Experience EMPTY = new Experience("", "", Collections.singletonList(Period.EMPTY));
    public Experience() {
    }

    public Experience(String name, String link, Period... periods) {
        this.name = name;
        this.link = link;
        this.periods = Arrays.asList(periods);
    }
    public Experience(String name, String link, List<Period> periods) {
        this.name = name;
        this.link = link;
        this.periods = periods;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    @Override
    public String toString() {
        return ("\n\n" + "Наименование: " + name + link + periods);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Experience that = (Experience) o;
        return Objects.equals(name, that.name) && Objects.equals(link, that.link) && Objects.equals(periods, that.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, link, periods);
    }
}
