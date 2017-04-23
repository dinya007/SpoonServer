package ru.mipt.restaurant.server.dao.impl;

import org.junit.Assert;
import org.junit.Test;
import ru.mipt.restaurant.server.ElasticTest;
import ru.mipt.restaurant.server.domain.Location;
import ru.mipt.restaurant.server.domain.OwnerPlace;

import java.util.List;

public class ElasticPlaceDaoTest extends ElasticTest {

    @Test
    public void testGetAll() throws Exception {
        elasticPlaceDao.save(ownerPlace1);
        elasticPlaceDao.save(ownerPlace2);

        timeout();

        List<OwnerPlace> result = elasticPlaceDao.getAll();
        Assert.assertEquals(2, result.size());

        Assert.assertTrue(result.contains(ownerPlace1));
        Assert.assertTrue(result.contains(ownerPlace2));
    }

    @Test
    public void testGetAllInArea() throws Exception {
        initPlaces();

        List<OwnerPlace> result = elasticPlaceDao.getAllInArea(new Location(56.0, 37.0), new Location(55.0, 38.0));

        Assert.assertEquals(3, result.size());
        Assert.assertTrue(result.contains(ownerPlace1));
        Assert.assertTrue(result.contains(ownerPlace2));
        Assert.assertTrue(result.contains(ownerPlace3));
    }

    @Test
    public void testUpdate() throws Exception {
        ElasticPlaceDao elasticPlaceDao = new ElasticPlaceDao();
        elasticPlaceDao.save(ownerPlace1);

        timeout();

        String address = "new address";
        ownerPlace1.setAddress(address);
        elasticPlaceDao.save(ownerPlace1);

        timeout();

        List<OwnerPlace> ownerPlaces = elasticPlaceDao.getAll();

        Assert.assertEquals(1, ownerPlaces.size());
        Assert.assertEquals(ownerPlace1, ownerPlaces.get(0));
        Assert.assertEquals(address, ownerPlaces.get(0).getAddress());
    }

    @Test
    public void testMultipleUpdates() throws Exception {
        ElasticPlaceDao elasticPlaceDao = new ElasticPlaceDao();
        elasticPlaceDao.save(ownerPlace1);
        elasticPlaceDao.save(ownerPlace2);
        elasticPlaceDao.save(ownerPlace3);
        elasticPlaceDao.save(ownerPlace4);

        timeout();

        String address1 = "new address 1";
        String address4 = "new address 4";
        ownerPlace1.setAddress(address1);
        ownerPlace4.setAddress(address4);
        elasticPlaceDao.save(ownerPlace1);
        elasticPlaceDao.save(ownerPlace2);
        elasticPlaceDao.save(ownerPlace3);
        elasticPlaceDao.save(ownerPlace4);

        timeout();

        List<OwnerPlace> ownerPlaces = elasticPlaceDao.getAll();

        Assert.assertEquals(4, ownerPlaces.size());
        Assert.assertEquals(address1, ownerPlaces.get(ownerPlaces.indexOf(ownerPlace1)).getAddress());
        Assert.assertEquals(ownerPlace2.getAddress(), ownerPlaces.get(ownerPlaces.indexOf(ownerPlace2)).getAddress());
        Assert.assertEquals(ownerPlace3.getAddress(), ownerPlaces.get(ownerPlaces.indexOf(ownerPlace3)).getAddress());
        Assert.assertEquals(address4, ownerPlaces.get(ownerPlaces.indexOf(ownerPlace4)).getAddress());
    }

}