package ru.mipt.restaurant.server.service.impl;

import org.elasticsearch.common.collect.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.mipt.restaurant.server.dao.PlaceDao;
import ru.mipt.restaurant.server.domain.Location;
import ru.mipt.restaurant.server.domain.OwnerPlace;
import ru.mipt.restaurant.server.domain.Sale;
import ru.mipt.restaurant.server.service.GeocodeService;
import ru.mipt.restaurant.server.service.OwnerPlaceService;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Service
public class OwnerPlaceServiceImpl implements OwnerPlaceService {

    private final PlaceDao placeDao;
    private final GeocodeService geocodeService;
    private final Environment environment;

    @Autowired
    public OwnerPlaceServiceImpl(PlaceDao placeDao, GeocodeService geocodeService, Environment environment) {
        this.placeDao = placeDao;
        this.geocodeService = geocodeService;
        this.environment = environment;
    }

    @PostConstruct
    private void init() {
        String[] activeProfiles = environment.getActiveProfiles();
        if (activeProfiles.length > 0) {
            if ("development".equals(activeProfiles[0])) {
                if (placeDao.getAll().isEmpty()) {
                    initPlaces();
                }
            }
        }
    }


    @Override
    public OwnerPlace update(OwnerPlace ownerPlace, boolean updateAddress) {
        if (updateAddress) {
            Tuple<String, Location> location = geocode(ownerPlace.getAddress());
            ownerPlace.setAddress(location.v1());
            ownerPlace.setLocation(location.v2());
        }
        return placeDao.save(ownerPlace);
    }

    @Override
    public OwnerPlace create(String name, String address, String description) {
        Tuple<String, Location> location = geocode(address);
        OwnerPlace ownerPlace = OwnerPlace.builder()
                .locationName(name)
                .location(location.v2())
                .address(location.v1())
                .description(description)
                .login(SecurityContextHolder.getContext().getAuthentication().getName())
                .build();
        return placeDao.save(ownerPlace);
    }

    @Override
    public String delete(String id) {
        return placeDao.delete(id);
    }

    @Override
    public List<OwnerPlace> getAllForSession() {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        return placeDao.getAllByOwner(login);
    }

    private Tuple<String, Location> geocode(String address) {
        Location location = geocodeService.geocode(address);
        String geocodedAddress = geocodeService.geocode(location);
        return new Tuple<>(geocodedAddress, location);
    }

    private void initPlaces() {
        Location location1 = new Location(55.754695, 37.621527);
        OwnerPlace ownerPlace1 = new OwnerPlace(location1, "ReStore", "Город Москва. Улица", "Скидки на планшеты и ноутбуки", "e1@mail.com", "login1");

        Location location2 = new Location(55.750763, 37.596108);
        OwnerPlace ownerPlace2 = new OwnerPlace(location2, "Starbucks", "Город Москва. Улица", "Кофе по цене чая", "e2@mail.com", "login2");

        Location location3 = new Location(55.756852, 37.614048);
        OwnerPlace ownerPlace3 = new OwnerPlace(location3, "NeVertu", "улица Моховая 15 Москва", "Магазин элитных мобильных телефонов", "toma-vesta@mail.ru", "toma");
        ownerPlace3.setSales(Arrays.asList(new Sale("Скидка 10% на все телефоны", true)));

        Location location4 = new Location(55.756126, 37.621163);
        OwnerPlace ownerPlace4 = new OwnerPlace(location4, "Чебуреки", "Никольская улица 4/5 Москва", "Самые вкусные чебуреки", "toma-vesta@mail.ru", "toma");
        ownerPlace4.setSales(Arrays.asList(new Sale("Скидка 10% на чебуреки с мясом", true), new Sale("Скидка 5% наи чебуреки с сыром и помидорами", false)));

        Location location5 = new Location(55.615384, 37.591808);
        OwnerPlace ownerPlace5 = new OwnerPlace(location5, "Магазин", "Чертановская улица 36 с 1 Москва", "Просто продукты", "toma-vesta@mail.ru", "toma");

        update(ownerPlace1, false);
        update(ownerPlace2, false);
        update(ownerPlace3, false);
        update(ownerPlace4, false);
        update(ownerPlace5, false);
    }

}
