package ru.mipt.restaurant.server.domain;

import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"uid"})
public class Visitor {

    private String uid;
    private String name;
    private String phoneNumber;
    private Date creationTime;
    private Date updateTime;
    @Singular
    private List<Place> places;

}
