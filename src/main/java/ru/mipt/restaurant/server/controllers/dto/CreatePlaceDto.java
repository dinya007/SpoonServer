package ru.mipt.restaurant.server.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePlaceDto {

    private String name;
    private String address;
    private String description;

}
