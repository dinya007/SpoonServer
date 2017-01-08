package ru.mipt.restaurant.server.dao.impl;

import ru.mipt.restaurant.server.dao.PlaceDao;
import ru.mipt.restaurant.server.domain.Location;
import ru.mipt.restaurant.server.domain.Owner;
import ru.mipt.restaurant.server.domain.Place;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryPlaceDao implements PlaceDao {

    private final ConcurrentHashMap<Location, Place> places;

    public InMemoryPlaceDao() {
        places = new ConcurrentHashMap<>();
        initMaps();
    }

    @Override
    public List<Place> getAll() {
        return new ArrayList<>(places.values());
    }

    @Override
    public List<Place> getAllInArea(Location topLeft, Location bottomRight) {
        return null;
    }

//    @Override
//    public List<Place> getAllInArea(Rectangle2D rectangle) {
//        return getAll()
//                .parallelStream()
//                .filter(discount -> CoordinateHelper.isInside(rectangle, discount.getLocation()))
//                .collect(Collectors.toList());
//    }

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
        return places.values().stream()
                .filter(place -> place.getOwnerEmail()
                        .equals(email))
                .collect(Collectors.toList());
    }

    @Override
    public List<Place> getAllByOwner(Owner owner) {
        return getAllByOwner(owner.getEmail());
    }

    private void initMaps() {
        Location location1 = new Location(55.754695, 37.621527);
        Place place1 = new Place(location1, "ReStore", "Скидки на планшеты и ноутбуки", "e1@mail.com");

        Location location2 = new Location(55.750763, 37.596108);
        Place place2 = new Place(location2, "Starbucks", "Кофе по цене чая", "e2@mail.com");

        Location location3 = new Location(55.756852, 37.614048);
        Place place3 = new Place(location3, "Vertu", "Шиш вам, а не скидки", "toma-vesta@mail.ru");

        Location location4 = new Location(0.0, 0.0);
        Place place4 = new Place(location4, "Чебуреки", "Чебуречная в РТС", "toma-vesta@mail.ru");

        places.put(location1, place1);
        places.put(location2, place2);
        places.put(location3, place3);
        places.put(location4, place4);
    }

}
