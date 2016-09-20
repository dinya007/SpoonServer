package ru.mipt.restaurant.server.service;

import ru.mipt.restaurant.server.domain.Coordinates;
import ru.mipt.restaurant.server.domain.Place;

import java.util.List;

public interface PlaceService {

    List<Place> getInsideRectangle(Coordinates topLeft, Coordinates bottomRight);

    List<Place> getAll();

    Place save(Place place);

    Place delete(Place place);

    List<Place> getAllForSession();


}
