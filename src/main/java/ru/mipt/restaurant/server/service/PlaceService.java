package ru.mipt.restaurant.server.service;

import ru.mipt.restaurant.server.domain.Location;
import ru.mipt.restaurant.server.domain.Place;

import java.util.List;

public interface PlaceService {

    List<Place> getInArea(Location topLeft, Location bottomRight);

    List<Place> getAll();

    Place save(Place place);

    Place delete(Place place);

    List<Place> getAllForSession();


}
