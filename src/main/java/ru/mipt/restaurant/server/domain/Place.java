package ru.mipt.restaurant.server.domain;

public class Place {

    private Location location;
    private String locationName;
    private String description;
    private float sale;
    private String ownerEmail;


    public Place() {
    }

    public Place(Location location, String locationName, int sale, String description, String ownerEmail) {
        this.location = location;
        this.locationName = locationName;
        this.sale = sale;
        this.description = description;
        this.ownerEmail = ownerEmail;
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

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }
}
