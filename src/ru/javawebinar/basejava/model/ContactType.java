package ru.javawebinar.basejava.model;

public enum ContactType {
    PHONE("Телефон"),
    SKYPE ("Skype"),
    EMAIL ("e-mail"),
    LINKEDIN ("LinkedIn"),
    GITHUB ("GitHub"),
    STACKOVERFLOW ("Stackoverflow"),
    HOMEPAGE ("Homepage");

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
