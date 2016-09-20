package ru.mipt.restaurant.server.dao.impl;

import org.springframework.stereotype.Component;
import ru.mipt.restaurant.server.dao.PlaceDao;
import ru.mipt.restaurant.server.domain.Coordinates;
import ru.mipt.restaurant.server.domain.Place;
import ru.mipt.restaurant.server.utils.CoordinateHelper;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class InMemoryPlaceDao implements PlaceDao {

    private final ConcurrentHashMap<Coordinates, Place> places;

    public InMemoryPlaceDao() {
        places = new ConcurrentHashMap<>();
        initMaps();
    }

    @Override
    public List<Place> getAll() {
        return new ArrayList<>(places.values());
    }

    @Override
    public List<Place> getAllInsideRectangle(Rectangle2D rectangle) {
        return getAll()
                .parallelStream()
                .filter(discount -> CoordinateHelper.isInside(rectangle, discount.getCoordinates()))
                .collect(Collectors.toList());
    }

    @Override
    public Place save(Place place) {
        return places.put(place.getCoordinates(), place);
    }

    @Override
    public Place delete(Place place) {
        return places.remove(place.getCoordinates());
    }

    @Override
    public List<Place> getAllByOwner(String email) {
        return places.values().stream()
                .filter(place -> place.getOwnerEmail()
                        .equals(email))
                .collect(Collectors.toList());
    }

    private void initMaps() {
        Coordinates coordinates1 = new Coordinates(55.754695, 37.621527);
        Place place1 = new Place(coordinates1, "ReStore", 10, "Скидки на планшеты и ноутбуки", "e1@mail.com");

        Coordinates coordinates2 = new Coordinates(55.750763, 37.596108);
        Place place2 = new Place(coordinates2, "Starbucks", 50, "Кофе по цене чая", "e2@mail.com");

        Coordinates coordinates3 = new Coordinates(55.756852, 37.614048);
        Place place3 = new Place(coordinates3, "Vertu", 0, "Шиш вам, а не скидки", "toma-vesta@mail.ru");

        Coordinates coordinates4 = new Coordinates(0.0, 0.0);
        Place place4 = new Place(coordinates4, "Чебуреки", 10, "Чебуречная в РТС", "toma-vesta@mail.ru");

        places.put(coordinates1, place1);
        places.put(coordinates2, place2);
        places.put(coordinates3, place3);
        places.put(coordinates4, place4);
    }

}
