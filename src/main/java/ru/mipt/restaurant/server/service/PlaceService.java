package ru.mipt.restaurant.server.service;

import ru.mipt.restaurant.server.domain.Coordinate;
import ru.mipt.restaurant.server.domain.Place;

import java.util.List;

public interface PlaceService {

    List<Place> getInsideRectangle(Coordinate topLeft, Coordinate bottomRight);

    List<Place> getAll();

    Place add(Place place);

    Place delete(Place place);

}
