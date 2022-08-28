package ru.javawebinar.basejava.model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection {

    public static final ListSection EMPTY = new ListSection(Collections.singletonList(""));
    private static final long serialVersionUID = 1L;

    private List<String> list;

    public ListSection() {}

    public ListSection(List<String> list) {
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }

    @Override
    public String toString() {
        return list.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return Objects.equals(list, that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }
}
