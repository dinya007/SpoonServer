package ru.mipt.restaurant.server.domain;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table("discounts")
public class Place {

    @PrimaryKey
    private Coordinates coordinates;
    @Column("location_name")
    private String locationName;
    @Column("description")
    private String description;
    @Column("sale")
    private int sale;
    @Column("owner_login")
    private String ownerEmail;


    public Place() {
    }

    public Place(Coordinates coordinates, String locationName, int sale, String description, String ownerEmail) {
        this.coordinates = coordinates;
        this.locationName = locationName;
        this.sale = sale;
        this.description = description;
        this.ownerEmail = ownerEmail;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
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

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }
}
