package ru.mipt.restaurant.server.domain;

import java.util.List;

public class Place {

    private Location location;
    private String locationName;
    private String description;
    private String ownerEmail;
    private List<Sale> sales;

    public Place() {
    }

    public Place(Location location, String locationName, String description, String ownerEmail) {
        this.location = location;
        this.locationName = locationName;
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

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    @Override
    public String toString() {
        return "Place{" +
                "location=" + location +
                ", locationName='" + locationName + '\'' +
                ", description='" + description + '\'' +
                ", ownerEmail='" + ownerEmail + '\'' +
                ", sales=" + sales +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Place place = (Place) o;

        return location != null ? location.equals(place.location) : place.location == null;
    }

    @Override
    public int hashCode() {
        return location != null ? location.hashCode() : 0;
    }
}
