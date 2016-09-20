package ru.mipt.restaurant.server.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mipt.restaurant.server.controllers.dto.PlaceDto;
import ru.mipt.restaurant.server.controllers.mapper.PlaceMapper;
import ru.mipt.restaurant.server.domain.Coordinates;
import ru.mipt.restaurant.server.service.PlaceService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/places")
public class PlaceController {

    private Logger logger = LoggerFactory.getLogger(PlaceController.class);

    private final PlaceService placeService;

    @Autowired
    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @RequestMapping("/{northLatitude}/{northLongitude}/{southLatitude}/{southLongitude}")
    public List<PlaceDto> getPlacesByCoordinates(@PathVariable("northLatitude") double northLatitude, @PathVariable("northLongitude") double northLongitude, @PathVariable("southLatitude") double southLatitude, @PathVariable("southLongitude") double southLongitude) {
        List<PlaceDto> result = placeService
                .getInsideRectangle(new Coordinates(northLatitude, northLongitude), new Coordinates(southLatitude, southLongitude))
                .parallelStream()
                .map(PlaceMapper::toDto)
                .collect(Collectors.toList());
        logger.debug(result.toString());
        return result;
    }


    @RequestMapping("/all")
    public List<PlaceDto> getAll() {
        return placeService.getAll()
                .parallelStream()
                .map(PlaceMapper::toDto)
                .collect(Collectors.toList());
    }

}
