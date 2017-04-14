package ru.mipt.restaurant.server.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mipt.restaurant.server.domain.Location;
import ru.mipt.restaurant.server.domain.Place;
import ru.mipt.restaurant.server.service.PlaceService;

import java.util.List;

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
    public List<Place> getPlacesByCoordinates(@PathVariable("northLatitude") double northLatitude, @PathVariable("northLongitude") double northLongitude, @PathVariable("southLatitude") double southLatitude, @PathVariable("southLongitude") double southLongitude) {
        List<Place> result = placeService
                .getInArea(new Location(northLatitude, northLongitude), new Location(southLatitude, southLongitude));
        logger.debug(result.toString());
        return result;
    }


    @RequestMapping("/all")
    public List<Place> getAll() {
        return placeService.getAll();
    }

}
