package ru.mipt.restaurant.server.controllers.dto;

import ru.mipt.restaurant.server.domain.Coordinate;

public class DiscountDto {

    private Coordinate coordinate;
    private String locationName;
    private String description;
    private int sale;

    public DiscountDto() {
    }

    public DiscountDto(Coordinate coordinate, String locationName, int sale, String description) {
        this.coordinate = coordinate;
        this.locationName = locationName;
        this.sale = sale;
        this.description = description;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
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

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

}
