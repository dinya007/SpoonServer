package ru.mipt.restaurant.server.dao;

import ru.mipt.restaurant.server.domain.Owner;

public interface OwnerDao {

    Owner save(Owner owner);

    Owner get(String login);

    Owner delete(Owner owner);

}
