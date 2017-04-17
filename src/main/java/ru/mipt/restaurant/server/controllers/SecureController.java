package ru.mipt.restaurant.server.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mipt.restaurant.server.controllers.dto.CreatePlaceDto;
import ru.mipt.restaurant.server.domain.Place;
import ru.mipt.restaurant.server.service.PlaceService;

import java.util.List;

@RestController
@RequestMapping("/secure")
public class SecureController {

    private final PlaceService placeService;
    private Logger logger = LoggerFactory.getLogger(PlaceController.class);

    @Autowired
    public SecureController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @RequestMapping("/info")
    public String getInfo() {
        return "This is secured";
    }

    @RequestMapping("/places/all-for-owner")
    public List<Place> getAllForOwner() {
        return placeService.getAllForSession();
    }

    @RequestMapping(value = "/place", method = RequestMethod.POST)
    public Place savePlace(@RequestBody Place place) {
        return placeService.update(place);
    }

    @RequestMapping(value = "/place", method = RequestMethod.PUT)
    public Place savePlace(@RequestBody CreatePlaceDto place) {
        return placeService.create(place.getName(), place.getAddress(), place.getDescription());
    }
}

