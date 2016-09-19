package ru.mipt.restaurant.server.dao;

import ru.mipt.restaurant.server.domain.Place;

import java.awt.geom.Rectangle2D;
import java.util.List;

public interface PlaceDao {

    List<Place> getAll();

    List<Place> getAllInsideRectangle(Rectangle2D rectangle);

    Place save(Place place);

    Place delete(Place place);

    List<Place> getAllByOwner(String email);
}
