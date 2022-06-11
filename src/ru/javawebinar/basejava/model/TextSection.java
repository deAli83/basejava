package ru.javawebinar.basejava.model;

public class TextSection extends AbstractSection {
    private static final long serialVersionUID = 1L;

    private final String text;

    public TextSection(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }
}
