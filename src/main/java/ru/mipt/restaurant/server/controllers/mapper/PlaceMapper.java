package ru.mipt.restaurant.server.controllers.mapper;

import ru.mipt.restaurant.server.controllers.dto.PlaceDto;
import ru.mipt.restaurant.server.domain.Place;

public class PlaceMapper {

    private PlaceMapper() {
        throw new AssertionError("This constructor hasn;t to bec called.");
    }

    public static PlaceDto toDto(Place place) {
        PlaceDto placeDto = new PlaceDto();

        placeDto.setCoordinates(place.getCoordinates());
        placeDto.setDescription(place.getDescription());
        placeDto.setLocationName(place.getLocationName());
        placeDto.setSale(place.getSale());
        placeDto.setOwnerLogin(place.getOwnerEmail());

        return placeDto;
    }

    public static Place toPlace(PlaceDto placeDto) {
        Place place = new Place();

        place.setCoordinates(placeDto.getCoordinates());
        place.setDescription(placeDto.getDescription());
        place.setLocationName(placeDto.getLocationName());
        place.setSale(placeDto.getSale());
        place.setOwnerEmail(placeDto.getOwnerLogin());

        return place;
    }

}
