package ru.mipt.restaurant.server.controllers.mapper;

import ru.mipt.restaurant.server.controllers.dto.PlaceDto;
import ru.mipt.restaurant.server.domain.Place;

public class PlaceMapper {

    private PlaceMapper() {
        throw new AssertionError("This constructor hasn;t to bec called.");
    }

    public static PlaceDto toDto(Place place) {
        PlaceDto placeDto = new PlaceDto();

        placeDto.setCoordinate(place.getCoordinate());
        placeDto.setDescription(place.getDescription());
        placeDto.setLocationName(place.getLocationName());
        placeDto.setSale(place.getSale());
        placeDto.setOwnerLogin(place.getOwnerLogin());

        return placeDto;
    }

    public static Place toPlace(PlaceDto placeDto) {
        Place place = new Place();

        place.setCoordinate(placeDto.getCoordinate());
        place.setDescription(placeDto.getDescription());
        place.setLocationName(placeDto.getLocationName());
        place.setSale(placeDto.getSale());
        place.setOwnerLogin(placeDto.getOwnerLogin());

        return place;
    }

}
