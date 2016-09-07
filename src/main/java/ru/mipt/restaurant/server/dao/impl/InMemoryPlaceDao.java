package ru.mipt.restaurant.server.dao.impl;

import org.springframework.stereotype.Component;
import ru.mipt.restaurant.server.dao.PlaceDao;
import ru.mipt.restaurant.server.domain.Coordinate;
import ru.mipt.restaurant.server.domain.Place;
import ru.mipt.restaurant.server.utils.CoordinateHelper;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class InMemoryPlaceDao implements PlaceDao {

    private final ConcurrentHashMap<Coordinate, Place> places;

    public InMemoryPlaceDao() {
        places = new ConcurrentHashMap<>();
        initMap();
    }

    @Override
    public List<Place> getAll() {
        return new ArrayList<>(places.values());
    }

    @Override
    public List<Place> getAllInsideRectangle(Rectangle2D rectangle) {
        return getAll()
                .parallelStream()
                .filter(discount -> CoordinateHelper.isInside(rectangle, discount.getCoordinate()))
                .collect(Collectors.toList());
    }

    @Override
    public Place save(Place place) {
        return places.put(place.getCoordinate(), place);
    }

    @Override
    public Place delete(Place place) {
        return places.remove(place.getCoordinate());
    }

    private void initMap() {
        Coordinate coordinate1 = new Coordinate(55.754695, 37.621527);
        Place place1 = new Place(coordinate1, "ReStore", 10, "Скидки на планшеты и ноутбуки", "login1");

        Coordinate coordinate2 = new Coordinate(55.750763, 37.596108);
        Place place2 = new Place(coordinate2, "Starbucks", 50, "Кофе по цене чая", "owner2");

        Coordinate coordinate3 = new Coordinate(55.756852, 37.614048);
        Place place3 = new Place(coordinate3, "Vertu", 0, "Шиш вам, а не скидки", "owner3");

        places.put(coordinate1, place1);
        places.put(coordinate2, place2);
        places.put(coordinate3, place3);
    }

}
