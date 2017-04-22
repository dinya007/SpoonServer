package ru.mipt.restaurant.server.service;

import ru.mipt.restaurant.server.domain.Location;
import ru.mipt.restaurant.server.domain.Place;

import java.util.List;

public interface PlaceService {

    List<Place> getWithActiveSalesInArea(Location topLeft, Location bottomRight);

    List<Place> getAll();

    Place update(Place place, boolean updateAddress);

    Place create(String name, String address, String description);

    String delete(String id);

    List<Place> getAllForSession();


}
