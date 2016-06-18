package ru.mipt.restaurant.server.domain;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table("discounts")
public class Discount {

    @PrimaryKey
    private Coordinate coordinate;
    @Column("location_name")
    private String locationName;
    @Column("description")
    private String description;
    @Column("sale")
    private int sale;

    public Discount() {
    }

    public Discount(Coordinate coordinate, String locationName, int sale, String description) {
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
