package ru.mipt.restaurant.server.domain;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public final class VisitorPlace extends Place {

    private Date startTime;
    private Date visitTime;
    private Integer visitorsAmount;

    @Builder
    public VisitorPlace(String id, Location location, String locationName, String address, String site, String phone, String description, String ownerEmail, Date startTime, Date visitTime, Integer visitorsAmount) {
        super(id, location, locationName, address, site, phone, description, ownerEmail);
        this.startTime = startTime;
        this.visitTime = visitTime;
        this.visitorsAmount = visitorsAmount;
    }
}
