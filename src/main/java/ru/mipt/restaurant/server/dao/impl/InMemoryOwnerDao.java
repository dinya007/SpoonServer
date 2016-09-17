package ru.mipt.restaurant.server.dao.impl;

import org.springframework.stereotype.Component;
import ru.mipt.restaurant.server.dao.OwnerDao;
import ru.mipt.restaurant.server.domain.Owner;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryOwnerDao implements OwnerDao {

    private final ConcurrentHashMap<String, Owner> owners;

    public InMemoryOwnerDao() {
        owners = new ConcurrentHashMap<>();
        initOwners();
    }

    @Override
    public Owner save(Owner owner) {
        return owners.put(owner.getName(), owner);
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
        Owner owner1 = new Owner();
        owner1.setName("login1");
        owner1.setPassword("password1");
        owner1.setEmail("e1@mail.com");
        owner1.setConfirmed(false);

        Owner owner2 = new Owner();
        owner2.setName("login2");
        owner2.setPassword("password2");
        owner2.setEmail("e2@mail.com");
        owner2.setConfirmed(false);

        Owner owner3 = new Owner();
        owner3.setName("login3");
        owner3.setPassword("password3");
        owner3.setEmail("e3@mail.com");
        owner3.setConfirmed(true);

        Owner toma = new Owner();
        toma.setName("toma");
        toma.setPassword("tomTomy4");
        toma.setEmail("toma-vesta@mail.ru");
        toma.setConfirmed(false);

        owners.put(owner1.getEmail(), owner1);
        owners.put(owner2.getEmail(), owner2);
        owners.put(owner3.getEmail(), owner3);
        owners.put(toma.getEmail(), toma);
    }
}
