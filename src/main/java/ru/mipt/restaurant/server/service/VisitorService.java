package ru.mipt.restaurant.server.service;

import ru.mipt.restaurant.server.domain.Visitor;

import java.util.List;

public interface VisitorService {

    Visitor get(String uid);

    Visitor save(Visitor visitor);

    List<Visitor> getByPlaceId(String placeId);

    String generateUID();

}
