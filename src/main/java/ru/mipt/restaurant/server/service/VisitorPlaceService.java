package ru.mipt.restaurant.server.service;

import ru.mipt.restaurant.server.domain.Location;
import ru.mipt.restaurant.server.domain.OwnerPlace;

import java.util.List;

public interface VisitorPlaceService {

    List<OwnerPlace> getWithActiveSalesInArea(Location topLeft, Location bottomRight);

    List<OwnerPlace> getAll();

}
