package ru.mipt.restaurant.server.domain;

import lombok.*;
import ru.mipt.restaurant.server.security.Role;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Owner {

    private String name;
    private String email;
    private String password;
    @Singular
    private List<Role> roles;
    private boolean isConfirmed;

}