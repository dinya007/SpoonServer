package ru.mipt.restaurant.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mipt.restaurant.server.dao.VisitorDao;
import ru.mipt.restaurant.server.domain.Visitor;
import ru.mipt.restaurant.server.service.VisitorService;

import java.util.List;
import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.trimToEmpty;

@Service
public class VisitorServiceImpl implements VisitorService {

    private final VisitorDao visitorDao;

    @Autowired
    public VisitorServiceImpl(VisitorDao visitorDao) {
        this.visitorDao = visitorDao;
    }

    @Override
    public Visitor get(String uid) {
        return visitorDao.get(uid);
    }

    @Override
    public Visitor save(Visitor visitor) {
        if (trimToEmpty(visitor.getUid()).isEmpty()) {
            throw new IllegalStateException("Visitor must contain uid " + visitor);
        }
        return visitorDao.save(visitor);
    }

    @Override
    public List<Visitor> getByPlaceId(String placeId) {
        return visitorDao.getByPlaceId(placeId);
    }

    @Override
    public String generateUID() {
        return UUID.randomUUID().toString();
    }


}
