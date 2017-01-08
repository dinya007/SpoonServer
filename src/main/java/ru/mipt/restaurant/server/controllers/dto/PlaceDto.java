package ru.mipt.restaurant.server.controllers.dto;

import ru.mipt.restaurant.server.domain.Location;
import ru.mipt.restaurant.server.domain.Sale;

import java.util.List;

public class PlaceDto {

    private Location location;
    private String locationName;
    private String description;
    private List<Sale> sales;
    private String ownerLogin;

    public PlaceDto() {
    }

    public PlaceDto(Location location, String locationName, String description, String ownerLogin) {
        this.location = location;
        this.locationName = locationName;
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

    public String getOwnerLogin() {
        return ownerLogin;
    }

    public void setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }
}
