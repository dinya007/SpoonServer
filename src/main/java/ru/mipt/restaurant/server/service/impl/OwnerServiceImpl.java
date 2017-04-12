package ru.mipt.restaurant.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mipt.restaurant.server.dao.OwnerDao;
import ru.mipt.restaurant.server.domain.Owner;
import ru.mipt.restaurant.server.service.OwnerService;

@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerDao ownerDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public OwnerServiceImpl(OwnerDao ownerDao, PasswordEncoder passwordEncoder) {
        this.ownerDao = ownerDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Owner register(Owner owner) {
        owner.setPassword(passwordEncoder.encode(owner.getPassword()));
        return ownerDao.save(owner);
    }

    @Override
    public Owner get(String login) {
        return ownerDao.get(login);
    }
}
