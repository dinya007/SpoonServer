package ru.mipt.restaurant.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.mipt.restaurant.server.dao.PlaceDao;
import ru.mipt.restaurant.server.domain.Coordinates;
import ru.mipt.restaurant.server.domain.Place;
import ru.mipt.restaurant.server.service.PlaceService;
import ru.mipt.restaurant.server.utils.CoordinateHelper;

import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {

    private final PlaceDao placeDao;

    @Autowired
    public PlaceServiceImpl(PlaceDao placeDao) {
        this.placeDao = placeDao;
    }

    @Override
    public List<Place> getInsideRectangle(Coordinates topLeft, Coordinates bottomRight) {
        return placeDao.getAllInsideRectangle(CoordinateHelper.toRectangle(topLeft, bottomRight));
    }

    @Override
    public List<Place> getAll() {
        return placeDao.getAll();
    }

    @Override
    public Place save(Place place) {
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

}
