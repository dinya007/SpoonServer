package ru.mipt.restaurant.server.domain;

import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public final class OwnerPlace extends Place {

    private String login;
    @Singular
    private List<Sale> sales;

    @Builder
    public OwnerPlace(Location location, String locationName, String address, String description, String ownerEmail, String login) {
        this.location = location;
        this.locationName = locationName;
        this.address = address;
        this.description = description;
        this.ownerEmail = ownerEmail;
        this.login = login;
    }

}
