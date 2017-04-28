package ru.mipt.restaurant.server.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mipt.restaurant.server.domain.OwnerPlace;
import ru.mipt.restaurant.server.service.VisitorPlaceService;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/places")
public class PlaceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlaceController.class);

    private final VisitorPlaceService visitorPlaceService;

    @Autowired
    public PlaceController(VisitorPlaceService visitorPlaceService) {
        this.visitorPlaceService = visitorPlaceService;
    }

    @RequestMapping("/{northLatitude}/{northLongitude}/{southLatitude}/{southLongitude}")
    public List<OwnerPlace> getPlacesByCoordinates(@PathVariable("northLatitude") double northLatitude, @PathVariable("northLongitude") double northLongitude, @PathVariable("southLatitude") double southLatitude, @PathVariable("southLongitude") double southLongitude, HttpSession session) {
//        List<OwnerPlace> result = visitorPlaceService
//                .getWithActiveSalesInArea(new Location(northLatitude, northLongitude), new Location(southLatitude, southLongitude));
        List<OwnerPlace> result = visitorPlaceService.getAll();
        LOGGER.debug(result.toString());
        return result;
    }


    @RequestMapping("/all")
    public List<OwnerPlace> getAll() {
        return visitorPlaceService.getAll();
    }

}
