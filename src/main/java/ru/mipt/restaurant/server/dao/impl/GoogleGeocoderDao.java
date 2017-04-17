package ru.mipt.restaurant.server.dao.impl;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.mipt.restaurant.server.dao.GeocoderDao;
import ru.mipt.restaurant.server.domain.Location;
import ru.mipt.restaurant.server.domain.geocode.GoogleResponse;

@Component
public class GoogleGeocoderDao implements GeocoderDao {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String GOOGLE_API_URL = "https://maps.googleapis.com/maps/api/geocode/json?language=ru&key=AIzaSyAZxThu4HQI_8XPfBtK2hjF1B5q99E1PNk";

    @Override
    public Location geocode(String address) {
        GoogleResponse response = restTemplate.getForObject(GOOGLE_API_URL + "&address=" + address, GoogleResponse.class);
        ru.mipt.restaurant.server.domain.geocode.Location location = response.getResults().get(0).getGeometry().getLocation();
        return new Location(location.getLat(), location.getLng());
    }

    @Override
    public String geocode(Location location) {
        GoogleResponse response = restTemplate.getForObject(GOOGLE_API_URL + "&latlng=" + location.getLat() + "," + location.getLon(), GoogleResponse.class);
        return response.getResults().get(0).getFormattedAddress();
    }

}
