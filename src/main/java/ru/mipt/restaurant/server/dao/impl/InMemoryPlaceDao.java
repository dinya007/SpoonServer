package ru.mipt.restaurant.server.dao.impl;

import ru.mipt.restaurant.server.dao.PlaceDao;
import ru.mipt.restaurant.server.domain.Location;
import ru.mipt.restaurant.server.domain.OwnerPlace;
import ru.mipt.restaurant.server.utils.CoordinateHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

//@Component
public class InMemoryPlaceDao implements PlaceDao {

    private final ConcurrentHashMap<String, OwnerPlace> places;

    public InMemoryPlaceDao() {
        places = new ConcurrentHashMap<>();
    }

    @Override
    public List<OwnerPlace> getAll() {
        return new ArrayList<>(places.values());
    }

    @Override
    public List<OwnerPlace> getAllInArea(Location topLeft, Location bottomRight) {
        return getAll()
                .parallelStream()
                .filter(discount -> CoordinateHelper.isInside(topLeft, bottomRight, discount.getLocation()))
                .collect(Collectors.toList());
    }

    @Override
    public OwnerPlace save(OwnerPlace ownerPlace) {
        return places.put(UUID.randomUUID().toString(), ownerPlace);
    }

    @Override
    public OwnerPlace get(String id) {
        return places.get(id);
    }

    @Override
    public String delete(String id) {
        places.remove(id);
        return id;
    }

    @Override
    public List<OwnerPlace> getAllByOwner(String email) {
        return places.values().stream()
                .filter(place -> place.getOwnerEmail()
                        .equals(email))
                .collect(Collectors.toList());
    }

}
