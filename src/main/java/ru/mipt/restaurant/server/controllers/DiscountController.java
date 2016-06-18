package ru.mipt.restaurant.server.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mipt.restaurant.server.controllers.dto.DiscountDto;
import ru.mipt.restaurant.server.controllers.mapper.DiscountMapper;
import ru.mipt.restaurant.server.domain.Coordinate;
import ru.mipt.restaurant.server.service.DiscountService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/discounts")
public class DiscountController {

    private Logger logger = LoggerFactory.getLogger(DiscountController.class);

    private final DiscountService discountService;

    @Autowired
    public DiscountController(DiscountService userDao) {
        this.discountService = userDao;
    }

    @RequestMapping("/{northLatitude}/{northLongitude}/{southLatitude}/{southLongitude}")
    public List<DiscountDto> getDiscountForPlace(@PathVariable("northLatitude") double northLatitude, @PathVariable("northLongitude") double northLongitude, @PathVariable("southLatitude") double southLatitude, @PathVariable("southLongitude") double southLongitude) {
        List<DiscountDto> result = discountService
                .getInsideRectangle(new Coordinate(northLatitude, northLongitude), new Coordinate(southLatitude, southLongitude))
                .parallelStream()
                .map(DiscountMapper::mapToDto)
                .collect(Collectors.toList());
        logger.debug(result.toString());
        return result;
    }


    @RequestMapping("/")
    public List<DiscountDto> getAll() {
        return discountService.getAll()
                .parallelStream()
                .map(DiscountMapper::mapToDto)
                .collect(Collectors.toList());
    }

}
