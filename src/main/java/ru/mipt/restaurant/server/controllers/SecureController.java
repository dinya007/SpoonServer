package ru.mipt.restaurant.server.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.mipt.restaurant.server.domain.Place;
import ru.mipt.restaurant.server.service.PlaceService;

import java.util.List;

@RestController
@RequestMapping("/secure")
public class SecureController {

    private Logger logger = LoggerFactory.getLogger(PlaceController.class);

    private final PlaceService placeService;

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
        return placeService.save(place);
    }
}

