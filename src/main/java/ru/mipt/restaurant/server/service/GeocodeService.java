package ru.mipt.restaurant.server.service;

import ru.mipt.restaurant.server.domain.Location;

public interface GeocodeService {

    Location geocode(String address);

    String geocode(Location location);

}
