package ru.mipt.restaurant.server.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Component;
import ru.mipt.restaurant.server.dao.OwnerDao;
import ru.mipt.restaurant.server.domain.Owner;

@Component
public class CassandraOwnerDao implements OwnerDao {

    private final CassandraOperations cassandraOperations;

    @Autowired
    public CassandraOwnerDao(CassandraOperations cassandraOperations) {
        this.cassandraOperations = cassandraOperations;
    }

    @Override
    public Owner save(Owner owner) {
        if(get(owner.getLogin()) == null) return cassandraOperations.insert(owner);
        return cassandraOperations.update(owner);
    }

    @Override
    public Owner get(String login) {
        return cassandraOperations.selectOne(String.format("select * from owners where login = %s", login), Owner.class);
    }

    @Override
    public Owner delete(Owner owner) {
        cassandraOperations.delete(owner);
        return owner;
    }

}
