package ru.mipt.restaurant.server.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mipt.restaurant.server.controllers.dto.CreatePlaceDto;
import ru.mipt.restaurant.server.controllers.dto.IdResponse;
import ru.mipt.restaurant.server.domain.OwnerPlace;
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
    public List<OwnerPlace> getAllForOwner() {
        return placeService.getAllForSession();
    }

    @RequestMapping(value = "/place", method = RequestMethod.PATCH)
    public OwnerPlace updatePlace(@RequestBody OwnerPlace ownerPlace) {
        return placeService.update(ownerPlace, false);
    }

    @RequestMapping(value = "/place", method = RequestMethod.POST)
    public OwnerPlace updatePlaceWithAddress(@RequestBody OwnerPlace ownerPlace) {
        return placeService.update(ownerPlace, true);
    }

    @RequestMapping(value = "/place", method = RequestMethod.PUT)
    public OwnerPlace createPlace(@RequestBody CreatePlaceDto place) {
        return placeService.create(place.getName(), place.getAddress(), place.getDescription());
    }

    @RequestMapping(value = "/place/{id}", method = RequestMethod.DELETE)
    public IdResponse deletePlace(@PathVariable String id) {
        return new IdResponse(placeService.delete(id));
    }
}

