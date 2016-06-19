package ru.mipt.restaurant.server.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Component;
import ru.mipt.restaurant.server.dao.DiscountDao;
import ru.mipt.restaurant.server.domain.Discount;
import ru.mipt.restaurant.server.utils.CoordinateHelper;

import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CassandraDiscountDao implements DiscountDao {

    private final CassandraOperations cassandraOperations;

    @Autowired
    public CassandraDiscountDao(CassandraOperations cassandraOperations) {
        this.cassandraOperations = cassandraOperations;
    }

    @Override
    public List<Discount> getAll() {
        return cassandraOperations.select("select * from discounts", Discount.class);
    }

    @Override
    public List<Discount> getAllInsideRectangle(Rectangle2D rectangle) {
        return getAll()
                .parallelStream()
                .filter(discount -> CoordinateHelper.isInside(rectangle, discount.getCoordinate()))
                .collect(Collectors.toList());
    }

    @Override
    public Discount save(Discount discount) {
        return cassandraOperations.insert(discount);
    }

    @Override
    public Discount delete(Discount discount) {
        cassandraOperations.delete(discount);
        return discount;
    }


}
