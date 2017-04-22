package ru.mipt.restaurant.server.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.mipt.restaurant.server.dao.OwnerDao;
import ru.mipt.restaurant.server.domain.Owner;
import ru.mipt.restaurant.server.security.Role;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryOwnerDao implements OwnerDao {

    private final ConcurrentHashMap<String, Owner> owners;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InMemoryOwnerDao(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        owners = new ConcurrentHashMap<>();
        initOwners();
    }

    @Override
    public Owner save(Owner owner) {
        return owners.put(owner.getEmail(), owner);
    }

    @Override
    public Owner get(String login) {
        return owners.get(login);
    }

    @Override
    public Owner delete(Owner owner) {
        return owners.remove(owner.getName());
    }

    private void initOwners() {
        Owner owner1 = Owner.builder()
                .name("login1")
                .password(passwordEncoder.encode("password1"))
                .email("e1@mail.com")
                .isConfirmed(false)
                .role(Role.VISITOR)
                .build();

        Owner owner2 = new Owner();
        owner2.setName("login2");
        owner2.setPassword(passwordEncoder.encode("password2"));
        owner2.setEmail("e2@mail.com");
        owner2.setConfirmed(false);

        Owner owner3 = new Owner();
        owner3.setName("login3");
        owner3.setPassword(passwordEncoder.encode("password3"));
        owner3.setEmail("e3@mail.com");
        owner3.setConfirmed(true);

        Owner toma = Owner.builder()
                .name("toma")
                .password(passwordEncoder.encode("123456"))
                .email("toma-vesta@mail.ru")
                .role(Role.OWNER)
                .isConfirmed(false)
                .build();

        owners.put(owner1.getEmail(), owner1);
        owners.put(owner2.getEmail(), owner2);
        owners.put(owner3.getEmail(), owner3);
        owners.put(toma.getEmail(), toma);
    }
}
