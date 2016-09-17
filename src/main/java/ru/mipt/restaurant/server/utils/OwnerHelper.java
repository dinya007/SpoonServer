package ru.mipt.restaurant.server.utils;

import ru.mipt.restaurant.server.controllers.dto.RegisterDto;
import ru.mipt.restaurant.server.domain.Owner;

public class OwnerHelper {

    public static Owner toOwner(RegisterDto registerDto) {
        Owner owner = new Owner();

        owner.setName(registerDto.getName());
        owner.setEmail(registerDto.getEmail());
        owner.setPassword(registerDto.getPassword());

        return owner;
    }

}
