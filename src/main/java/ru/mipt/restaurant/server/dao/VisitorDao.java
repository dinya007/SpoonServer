package ru.mipt.restaurant.server.dao;

import ru.mipt.restaurant.server.domain.Place;
import ru.mipt.restaurant.server.domain.Visitor;

import java.util.List;
import java.util.Map;

public interface VisitorDao {


    Visitor getByUid(String uid);

    List<Visitor> getByPlaceId(String id);

    void savePlaces(String uid, List<Place> places);

    Map<String, List<Place>> getAll();
}
