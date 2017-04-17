package ru.mipt.restaurant.server.domain;

import lombok.*;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode(of = {"location"})
@AllArgsConstructor
public class Place {

    private String id;
    private Location location;
    private String locationName;
    private String address;
    private String description;
    private String ownerEmail;
    @Singular
    private List<Sale> sales;

    public Place() {
    }

    public Place(Location location, String locationName,String address, String description, String ownerEmail) {
        this.location = location;
        this.locationName = locationName;
        this.address = address;
        this.description = description;
        this.ownerEmail = ownerEmail;
    }

}
