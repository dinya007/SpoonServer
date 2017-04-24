package ru.mipt.restaurant.server.dao.impl;

public enum Index {

    PLACES("places"),
    VISITORS("visitors");

    private final String name;

    Index(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
