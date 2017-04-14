package ru.mipt.restaurant.server.domain;

public class Sale {

    private String description;
    private boolean active;

    public Sale() {
    }

    public Sale(String description, boolean active) {
        this.description = description;
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "description='" + description + '\'' +
                ", active=" + active +
                '}';
    }
}
