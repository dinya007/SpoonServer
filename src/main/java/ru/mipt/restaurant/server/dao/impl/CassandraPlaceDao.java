package ru.mipt.restaurant.server.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Component;
import ru.mipt.restaurant.server.dao.PlaceDao;
import ru.mipt.restaurant.server.domain.Place;
import ru.mipt.restaurant.server.utils.CoordinateHelper;

import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CassandraPlaceDao implements PlaceDao {

    private final CassandraOperations cassandraOperations;

    @Autowired
    public CassandraPlaceDao(CassandraOperations cassandraOperations) {
        this.cassandraOperations = cassandraOperations;
    }

    @Override
    public List<Place> getAll() {
        return cassandraOperations.select("select * from discounts", Place.class);
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
        return cassandraOperations.insert(place);
    }

    @Override
    public Place delete(Place place) {
        cassandraOperations.delete(place);
        return place;
    }

    @Override
    public List<Place> getAllByOwner(String email) {
        return null;
    }


}
