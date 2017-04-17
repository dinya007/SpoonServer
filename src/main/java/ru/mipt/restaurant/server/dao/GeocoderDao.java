package ru.mipt.restaurant.server.dao;

import ru.mipt.restaurant.server.domain.Location;

public interface GeocoderDao {

    Location geocode(String address);

    String geocode(Location location);

}
