package ru.mipt.restaurant.server.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mipt.restaurant.server.controllers.dto.CreatePlaceDto;
import ru.mipt.restaurant.server.controllers.dto.IdResponse;
import ru.mipt.restaurant.server.domain.OwnerPlace;
import ru.mipt.restaurant.server.service.OwnerPlaceService;

import java.util.List;

@RestController
@RequestMapping("/secure")
public class SecurePlaceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlaceController.class);

    private final OwnerPlaceService ownerPlaceService;

    @Autowired
    public SecurePlaceController(OwnerPlaceService ownerPlaceService) {
        this.ownerPlaceService = ownerPlaceService;
    }

    @RequestMapping("/info")
    public String getInfo() {
        return "This is secured";
    }

    @RequestMapping("/places/all-for-owner")
    public List<OwnerPlace> getAllForOwner() {
        return ownerPlaceService.getAllForSession();
    }

    @RequestMapping(value = "/place", method = RequestMethod.PATCH)
    public OwnerPlace updatePlace(@RequestBody OwnerPlace ownerPlace) {
        return ownerPlaceService.update(ownerPlace, false);
    }

    @RequestMapping(value = "/place", method = RequestMethod.POST)
    public OwnerPlace updatePlaceWithAddress(@RequestBody OwnerPlace ownerPlace) {
        return ownerPlaceService.update(ownerPlace, true);
    }

    @RequestMapping(value = "/place", method = RequestMethod.PUT)
    public OwnerPlace createPlace(@RequestBody CreatePlaceDto place) {
        return ownerPlaceService.create(place.getName(), place.getAddress(), place.getDescription());
    }

    @RequestMapping(value = "/place/{id}", method = RequestMethod.DELETE)
    public IdResponse deletePlace(@PathVariable String id) {
        return new IdResponse(ownerPlaceService.delete(id));
    }
}

