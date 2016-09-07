package ru.mipt.restaurant.server.dao.impl;

import org.springframework.stereotype.Component;
import ru.mipt.restaurant.server.dao.DiscountDao;
import ru.mipt.restaurant.server.domain.Coordinate;
import ru.mipt.restaurant.server.domain.Discount;
import ru.mipt.restaurant.server.utils.CoordinateHelper;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class InMemoryDiscountDao implements DiscountDao {

    private final ConcurrentHashMap<Coordinate, Discount> discounts;

    public InMemoryDiscountDao() {
        discounts = new ConcurrentHashMap<>();
        initMap();
    }

    @Override
    public List<Discount> getAll() {
        return new ArrayList<>(discounts.values());
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
        return discounts.put(discount.getCoordinate(), discount);
    }

    @Override
    public Discount delete(Discount discount) {
        return discounts.remove(discount.getCoordinate());
    }

    private void initMap() {
        Coordinate coordinate1 = new Coordinate(55.754695, 37.621527);
        Discount discount1 = new Discount(coordinate1, "ReStore", 10, "Скидки на планшеты и ноутбуки");

        Coordinate coordinate2 = new Coordinate(55.750763, 37.596108);
        Discount discount2 = new Discount(coordinate2, "Starbucks", 50, "Кофе по цене чая");

        Coordinate coordinate3 = new Coordinate(55.756852, 37.614048);
        Discount discount3 = new Discount(coordinate3, "Vertu", 0, "Шиш вам, а не скидки");

        discounts.put(coordinate1, discount1);
        discounts.put(coordinate2, discount2);
        discounts.put(coordinate3, discount3);
    }

}
