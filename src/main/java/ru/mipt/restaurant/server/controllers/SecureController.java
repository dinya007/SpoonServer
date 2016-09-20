package ru.mipt.restaurant.server.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.mipt.restaurant.server.controllers.dto.PlaceDto;
import ru.mipt.restaurant.server.controllers.mapper.PlaceMapper;
import ru.mipt.restaurant.server.domain.Place;
import ru.mipt.restaurant.server.service.PlaceService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/secure")
public class SecureController {

    private Logger logger = LoggerFactory.getLogger(PlaceController.class);

    private final PlaceService placeService;

    @Autowired
    public SecureController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @RequestMapping("/all")
    public String getAllSecureInfo() {
        return "This is secured";
    }

    @RequestMapping("/places/all-for-owner")
    public List<PlaceDto> getAllForOwner() {
        return placeService.getAllForSession()
                .stream()
                .map(PlaceMapper::toDto)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/places/save", method = RequestMethod.POST)
    public PlaceDto savePlace(@RequestBody PlaceDto placeDto) {
        Place place = PlaceMapper.toPlace(placeDto);
        placeService.save(place);
        return PlaceMapper.toDto(place);
    }
}

