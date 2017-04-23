package ru.mipt.restaurant.server.dao;

import ru.mipt.restaurant.server.domain.Location;
import ru.mipt.restaurant.server.domain.OwnerPlace;

import java.util.List;

public interface PlaceDao {

    List<OwnerPlace> getAll();

    List<OwnerPlace> getAllInArea(Location topLeft, Location bottomRight);

    OwnerPlace save(OwnerPlace ownerPlace);

    OwnerPlace get(String id);

    String delete(String id);

    List<OwnerPlace> getAllByOwner(String login);

}
