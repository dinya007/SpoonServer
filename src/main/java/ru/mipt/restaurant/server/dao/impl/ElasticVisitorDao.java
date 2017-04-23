package ru.mipt.restaurant.server.dao.impl;

import ru.mipt.restaurant.server.dao.VisitorDao;
import ru.mipt.restaurant.server.domain.Place;
import ru.mipt.restaurant.server.domain.Visitor;

import java.util.List;
import java.util.Map;

public class ElasticVisitorDao implements VisitorDao {

    @Override
    public Visitor getByUid(String uid) {
        return null;
    }

    @Override
    public List<Visitor> getByPlaceId(String id) {
        return null;
    }

    @Override
    public void savePlaces(String uid, List<Place> places) {

    }

    @Override
    public Map<String, List<Place>> getAll() {
        return null;
    }
}
