package ru.mipt.restaurant.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.mipt.restaurant.server.dao.PlaceDao;
import ru.mipt.restaurant.server.domain.Location;
import ru.mipt.restaurant.server.domain.Place;
import ru.mipt.restaurant.server.domain.Sale;
import ru.mipt.restaurant.server.service.GeocodeService;
import ru.mipt.restaurant.server.service.PlaceService;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {

    private final PlaceDao placeDao;
    private final GeocodeService geocodeService;
    private final Environment environment;

    @Autowired
    public PlaceServiceImpl(PlaceDao placeDao, GeocodeService geocodeService, Environment environment) {
        this.placeDao = placeDao;
        this.geocodeService = geocodeService;
        this.environment = environment;
    }

    @PostConstruct
    private void init() {
        if ("development".equals(environment.getActiveProfiles()[0])) {
//            initPlaces();
        }
    }

    @Override
    public List<Place> getInArea(Location topLeft, Location bottomRight) {
        return placeDao.getAllInArea(topLeft, bottomRight);
    }

    @Override
    public List<Place> getAll() {
        return placeDao.getAll();
    }

    @Override
    public Place update(Place place) {
        return placeDao.save(place);
    }

    @Override
    public Place create(String name, String address, String description) {
        Location location = geocodeService.geocode(address);
        Place place = Place.builder()
                .locationName(name)
                .location(location)
                .address(geocodeService.geocode(location))
                .description(description)
                .ownerEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .build();
        return placeDao.save(place);
    }

    @Override
    public Place delete(Place place) {
        return placeDao.delete(place);
    }

    @Override
    public List<Place> getAllForSession() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return placeDao.getAllByOwner(email);
    }

    private void initPlaces() {
        Location location1 = new Location(55.754695, 37.621527);
        Place place1 = new Place(location1, "ReStore", "Город Москва. Улица", "Скидки на планшеты и ноутбуки", "e1@mail.com");

        Location location2 = new Location(55.750763, 37.596108);
        Place place2 = new Place(location2, "Starbucks", "Город Москва. Улица", "Кофе по цене чая", "e2@mail.com");

        Location location3 = new Location(55.756852, 37.614048);
        Place place3 = new Place(location3, "NeVertu", "улица Моховая 15 Москва", "Магазин элитных мобильных телефонов", "toma-vesta@mail.ru");
        place3.setSales(Arrays.asList(new Sale("Скидка 10% на все телефоны", true)));

        Location location4 = new Location(55.756126, 37.621163);
        Place place4 = new Place(location4, "Чебуреки", "Никольская улица 4/5 Москва", "Самые вкусные чебуреки", "toma-vesta@mail.ru");
        place4.setSales(Arrays.asList(new Sale("Скидка 10% на чебуреки с мясом", true), new Sale("Скидка 5% наи чебуреки с сыром и помидорами", false)));

        Location location5 = new Location(55.615384, 37.591808);
        Place place5 = new Place(location5, "Магазин", "Чертановская улица 36 с 1 Москва", "Просто продукты", "toma-vesta@mail.ru");

        update(place1);
        update(place2);
        update(place3);
        update(place4);
        update(place5);
    }

}
