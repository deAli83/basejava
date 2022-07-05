package ru.javawebinar.basejava.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Experience implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String link;
    private List<Period> periods;

    public Experience() {
    }

    public Experience(String name, String link, List<Period> periods) {
        this.name = name;
        this.link = link;
        this.periods = periods;
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
