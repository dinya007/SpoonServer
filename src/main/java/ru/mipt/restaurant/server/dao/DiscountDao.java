package ru.mipt.restaurant.server.dao;

import ru.mipt.restaurant.server.domain.Discount;

import java.awt.geom.Rectangle2D;
import java.util.List;

public interface DiscountDao {

    List<Discount> getAll();

    List<Discount> getAllInsideRectangle(Rectangle2D rectangle);

    Discount save(Discount discount);

    Discount delete(Discount discount);
}
