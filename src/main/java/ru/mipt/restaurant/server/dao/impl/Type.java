package ru.mipt.restaurant.server.dao.impl;

public enum Type {

    RESTAURANT("restaurant"),
    VISITOR("visitor");

    private final String name;

    Type(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
