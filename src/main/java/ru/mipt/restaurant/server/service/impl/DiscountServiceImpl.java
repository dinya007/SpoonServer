package ru.mipt.restaurant.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mipt.restaurant.server.dao.DiscountDao;
import ru.mipt.restaurant.server.domain.Coordinate;
import ru.mipt.restaurant.server.domain.Discount;
import ru.mipt.restaurant.server.service.DiscountService;
import ru.mipt.restaurant.server.utils.CoordinateHelper;

import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {

    private final DiscountDao discountDao;

    @Autowired
    public DiscountServiceImpl(DiscountDao discountDao) {
        this.discountDao = discountDao;
    }

    @Override
    public List<Discount> getInsideRectangle(Coordinate topLeft, Coordinate bottomRight) {
        return discountDao.getAllInsideRectangle(CoordinateHelper.toRectangle(topLeft, bottomRight));
    }

    @Override
    public List<Discount> getAll() {
        return discountDao.getAll();
    }

    @Override
    public Discount add(Discount discount) {
        return discountDao.save(discount);
    }

    @Override
    public Discount delete(Discount discount) {
        return discountDao.delete(discount);
    }

}
