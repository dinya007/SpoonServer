package ru.mipt.restaurant.server.service;

import ru.mipt.restaurant.server.domain.Owner;

public interface OwnerService {

    Owner register(Owner owner);

    Owner get(String login);

}
