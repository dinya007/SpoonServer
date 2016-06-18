package ru.mipt.restaurant.server.service;

import ru.mipt.restaurant.server.domain.Coordinate;
import ru.mipt.restaurant.server.domain.Discount;

import java.util.List;

public interface DiscountService {

    List<Discount> getInsideRectangle(Coordinate topLeft, Coordinate bottomRight);

    List<Discount> getAll();

}
