package ru.mipt.restaurant.server.dao.impl;

public enum Index {

    PLACES("places");

    private final String name;

    Index(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
