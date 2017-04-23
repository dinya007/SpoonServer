package ru.mipt.restaurant.server.service;

import ru.mipt.restaurant.server.domain.Location;
import ru.mipt.restaurant.server.domain.OwnerPlace;

import java.util.List;

public interface PlaceService {

    List<OwnerPlace> getWithActiveSalesInArea(Location topLeft, Location bottomRight);

    List<OwnerPlace> getAll();

    OwnerPlace update(OwnerPlace ownerPlace, boolean updateAddress);

    OwnerPlace create(String name, String address, String description);

    String delete(String id);

    List<OwnerPlace> getAllForSession();


}
