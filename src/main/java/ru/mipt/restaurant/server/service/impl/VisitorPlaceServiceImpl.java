package ru.mipt.restaurant.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mipt.restaurant.server.dao.PlaceDao;
import ru.mipt.restaurant.server.domain.Location;
import ru.mipt.restaurant.server.domain.OwnerPlace;
import ru.mipt.restaurant.server.domain.Sale;
import ru.mipt.restaurant.server.service.VisitorPlaceService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitorPlaceServiceImpl implements VisitorPlaceService {

    private final PlaceDao placeDao;

    @Autowired
    public VisitorPlaceServiceImpl(PlaceDao placeDao) {
        this.placeDao = placeDao;
    }

    @Override
    public List<OwnerPlace> getWithActiveSalesInArea(Location topLeft, Location bottomRight) {
        return placeDao.getAllInArea(topLeft, bottomRight)
                .stream().filter(this::filterActive)
                .map(this::removeLogin)
                .collect(Collectors.toList());
    }

    @Override
    public List<OwnerPlace> getAll() {
        return placeDao.getAll().stream()
                .filter(this::filterActive)
                .map(this::removeLogin)
                .collect(Collectors.toList());
    }

    private boolean filterActive(OwnerPlace place) {
        List<Sale> sales = place.getSales();
        if (sales == null) {
            return false;
        }
        sales.removeIf(sale -> !sale.isActive());
        return sales.size() != 0;
    }

    private OwnerPlace removeLogin(OwnerPlace place) {
        place.setLogin(null);
        return place;
    }
}
