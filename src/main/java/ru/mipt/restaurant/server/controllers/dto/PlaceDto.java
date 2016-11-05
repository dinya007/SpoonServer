package ru.mipt.restaurant.server.controllers.dto;

import ru.mipt.restaurant.server.domain.Location;

public class PlaceDto {

    private Location location;
    private String locationName;
    private String description;
    private float sale;
    private String ownerLogin;

    public PlaceDto() {
    }

    public PlaceDto(Location location, String locationName, int sale, String description, String ownerLogin) {
        this.location = location;
        this.locationName = locationName;
        this.sale = sale;
        this.description = description;
        this.ownerLogin = ownerLogin;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getSale() {
        return sale;
    }

    public void setSale(float sale) {
        this.sale = sale;
    }

    public String getOwnerLogin() {
        return ownerLogin;
    }

    public void setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }
}
