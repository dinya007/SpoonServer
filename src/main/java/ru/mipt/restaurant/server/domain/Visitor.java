package ru.mipt.restaurant.server.domain;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"uid"})
public class Visitor {

    private String uid;
    private String name;
    private String phoneNumber;
    @Singular
    private List<VisitorPlace> places;

}
