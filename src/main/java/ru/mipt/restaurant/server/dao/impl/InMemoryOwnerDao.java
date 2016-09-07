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
        return owners.put(owner.getLogin(), owner);
    }

    @Override
    public Owner get(String login) {
        return owners.get(login);
    }

    @Override
    public Owner delete(Owner owner) {
        return owners.remove(owner.getLogin());
    }

    private void initOwners() {
        Owner owner1 = new Owner();
        owner1.setLogin("login1");
        owner1.setPassword("password1");
        owner1.setEmail("e1@mail.com");
        owner1.setConfirmed(false);

        Owner owner2 = new Owner();
        owner2.setLogin("login2");
        owner2.setPassword("password2");
        owner2.setEmail("e2@mail.com");
        owner2.setConfirmed(false);

        Owner owner3 = new Owner();
        owner3.setLogin("login3");
        owner3.setPassword("password3");
        owner3.setEmail("e3@mail.com");
        owner3.setConfirmed(true);
        owner3.setConfirmed(true);

        owners.put(owner1.getLogin(), owner1);
        owners.put(owner2.getLogin(), owner2);
        owners.put(owner3.getLogin(), owner3);
    }
}
