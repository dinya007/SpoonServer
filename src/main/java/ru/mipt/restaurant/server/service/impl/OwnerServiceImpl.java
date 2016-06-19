package ru.mipt.restaurant.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mipt.restaurant.server.dao.OwnerDao;
import ru.mipt.restaurant.server.domain.Owner;
import ru.mipt.restaurant.server.service.OwnerService;

@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerDao ownerDao;

    @Autowired
    public OwnerServiceImpl(OwnerDao ownerDao) {
        this.ownerDao = ownerDao;
    }

    @Override
    public Owner register(Owner owner) {
        return ownerDao.save(owner);
    }

    @Override
    public Owner get(String login) {
        return ownerDao.get(login);
    }
}
