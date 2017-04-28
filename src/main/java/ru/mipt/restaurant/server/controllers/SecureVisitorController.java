package ru.mipt.restaurant.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.mipt.restaurant.server.domain.Visitor;
import ru.mipt.restaurant.server.service.VisitorService;

import java.util.List;

@RestController
@RequestMapping("/secure/visitor")
public class SecureVisitorController {

    private final VisitorService visitorService;

    @Autowired
    public SecureVisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @RequestMapping(value = "/{placeId}", method = RequestMethod.GET)
    public List<Visitor> getAllForPlace(@PathVariable String placeId) {
        return visitorService.getByPlaceId(placeId);
    }

}
