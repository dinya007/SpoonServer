package ru.mipt.restaurant.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mipt.restaurant.server.dao.GeocoderDao;
import ru.mipt.restaurant.server.domain.Location;
import ru.mipt.restaurant.server.service.GeocodeService;

@Service
public class GeocodeServiceImpl implements GeocodeService {

    private final GeocoderDao geocoderDao;

    @Autowired
    public GeocodeServiceImpl(GeocoderDao geocoderDao) {
        this.geocoderDao = geocoderDao;
    }

    @Override
    public Location geocode(String address) {
        return geocoderDao.geocode(address);
    }

    @Override
    public String geocode(Location location) {
        return geocoderDao.geocode(location);
    }
}
