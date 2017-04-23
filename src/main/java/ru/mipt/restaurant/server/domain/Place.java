package ru.mipt.restaurant.server.domain;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode(of = {"location"})
@NoArgsConstructor
@AllArgsConstructor
public class Place implements Serializable {

    private String id;
    private String login;
    private Location location;
    private String locationName;
    private String address;
    private String site;
    private String phone;
    private String description;
    private String ownerEmail;
    @Singular
    private List<Sale> sales;

    public Place(Location location, String locationName, String address, String description, String ownerEmail, String login) {
        this.location = location;
        this.locationName = locationName;
        this.address = address;
        this.description = description;
        this.ownerEmail = ownerEmail;
        this.login = login;
    }

}
