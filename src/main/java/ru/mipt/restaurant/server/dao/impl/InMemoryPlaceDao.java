package ru.mipt.restaurant.server.dao.impl;

import ru.mipt.restaurant.server.dao.PlaceDao;
import ru.mipt.restaurant.server.domain.Location;
import ru.mipt.restaurant.server.domain.Place;
import ru.mipt.restaurant.server.utils.CoordinateHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

//@Profile("development")
//@Component
public class InMemoryPlaceDao implements PlaceDao {

    private final ConcurrentHashMap<String, Place> places;

    public InMemoryPlaceDao() {
        places = new ConcurrentHashMap<>();
    }

    @Override
    public List<Place> getAll() {
        return new ArrayList<>(places.values());
    }

    @Override
    public List<Place> getAllInArea(Location topLeft, Location bottomRight) {
        return getAll()
                .parallelStream()
                .filter(discount -> CoordinateHelper.isInside(topLeft, bottomRight, discount.getLocation()))
                .collect(Collectors.toList());
    }

    @Override
    public Place save(Place place) {
        return places.put(UUID.randomUUID().toString(), place);
    }

    @Override
    public Place get(String id) {
        return places.get(id);
    }

    @Override
    public String delete(String id) {
        places.remove(id);
        return id;
    }

    @Override
    public List<Place> getAllByOwner(String email) {
        return places.values().stream()
                .filter(place -> place.getOwnerEmail()
                        .equals(email))
                .collect(Collectors.toList());
    }

}
