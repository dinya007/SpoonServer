package ru.mipt.restaurant.server.controllers;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.mipt.restaurant.server.controllers.dto.PlaceDto;
import ru.mipt.restaurant.server.domain.Coordinates;

@Ignore
public class PlaceControllerTest {


    private static final String appUrl = "http://127.0.0.1:8080";

    @Test
    public void addPlace() throws Exception {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> allPlaces = restTemplate.getForEntity(appUrl + "/places/all", String.class);

        Coordinates coordinates = new Coordinates(0.0, 0.0);
        String description = "New Unique Description";
        PlaceDto placeDto = new PlaceDto(coordinates, "New Location", 4, description, "login1");

        Assert.assertTrue(!allPlaces.getBody().contains(description));

        restTemplate.put(appUrl + "places/save", placeDto);

        allPlaces = restTemplate.getForEntity(appUrl + "/places/all", String.class);

        Assert.assertTrue(allPlaces.getBody().contains(description));

    }

}