package com.uirise.webapp.model;

public enum ContactType {
    PHONE("Телефон"),
    ADRESS("Адрес"),
    EMAIL("e-mail");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}