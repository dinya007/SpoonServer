package ru.mipt.restaurant.server.dao;

import ru.mipt.restaurant.server.domain.Location;
import ru.mipt.restaurant.server.domain.Place;

import java.awt.geom.Rectangle2D;
import java.util.List;

public interface PlaceDao {

    List<Place> getAll();

    List<Place> getAllInsideRectangle(Location topLeft, Location bottomRight);

    Place save(Place place);

    Place delete(Place place);

    List<Place> getAllByOwner(String email);
}
