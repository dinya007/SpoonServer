package ru.mipt.restaurant.server.dao;

import ru.mipt.restaurant.server.domain.Visitor;

import java.util.List;

public interface VisitorDao {

    Visitor getByUid(String uid);

    List<Visitor> getByPlaceId(String id);

    Visitor save(Visitor visitor);

    List<Visitor> getAll();

}
