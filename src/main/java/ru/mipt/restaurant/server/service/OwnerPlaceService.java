package ru.mipt.restaurant.server.service;

import ru.mipt.restaurant.server.domain.OwnerPlace;

import java.util.List;

public interface OwnerPlaceService {

    OwnerPlace update(OwnerPlace ownerPlace, boolean updateAddress);

    OwnerPlace create(String name, String address, String description);

    String delete(String id);

    List<OwnerPlace> getAllForSession();


}
