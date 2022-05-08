package ru.javawebinar.basejava.model;

import java.util.List;

public class ListSection extends AbstractSection {
    private final List<String> list;

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

}
