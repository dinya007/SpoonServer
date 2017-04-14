package ru.mipt.restaurant.server.dao.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.mipt.restaurant.server.dao.PlaceDao;
import ru.mipt.restaurant.server.domain.Location;
import ru.mipt.restaurant.server.domain.Owner;
import ru.mipt.restaurant.server.domain.Place;
import ru.mipt.restaurant.server.domain.Sale;
import ru.mipt.restaurant.server.utils.CoordinateHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

//@Profile("development")
//@Component
public class InMemoryPlaceDao implements PlaceDao {

    private final ConcurrentHashMap<Location, Place> places;

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
        return places.put(place.getLocation(), place);
    }

    @Override
    public Place delete(Place place) {
        return places.remove(place.getLocation());
    }

    @Override
    public List<Place> getAllByOwner(String email) {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return places.values().stream()
                .filter(place -> place.getOwnerEmail()
                        .equals(email))
                .collect(Collectors.toList());
    }

    @Override
    public List<Place> getAllByOwner(Owner owner) {
        return getAllByOwner(owner.getEmail());
    }


}
