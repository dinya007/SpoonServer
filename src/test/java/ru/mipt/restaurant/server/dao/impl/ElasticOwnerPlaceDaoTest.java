package ru.mipt.restaurant.server.dao.impl;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.mipt.restaurant.server.ElasticTest;
import ru.mipt.restaurant.server.domain.Location;
import ru.mipt.restaurant.server.domain.OwnerPlace;
import ru.mipt.restaurant.server.domain.Sale;

import java.util.List;

public class ElasticOwnerPlaceDaoTest extends ElasticTest {



    private Location location1 = new Location(55.754695, 37.621527);
    private OwnerPlace ownerPlace1 = new OwnerPlace(location1, "ReStore", "Address 1", "Скидки на планшеты и ноутбуки", "e1@mail.com", "login1");

    private Location location2 = new Location(55.750763, 37.596108);
    private OwnerPlace ownerPlace2 = new OwnerPlace(location2, "Starbucks", "Address 2", "Кофе по цене чая", "e2@mail.com", "login2");

    private Location location3 = new Location(55.756852, 37.614048);
    private OwnerPlace ownerPlace3 = new OwnerPlace(location3, "Vertu", "Address 3", "Шиш вам, а не скидки", "toma-vesta@mail.ru", "toma");

    private Location location4 = new Location(0.0, 0.0);
    private OwnerPlace ownerPlace4 = new OwnerPlace(location4, "Чебуреки", "Address 4", "Чебуречная в РТС", "toma-vesta@mail.ru", "toma");

    private Sale sale1 = new Sale("some description", true);
    private Sale sale2 = new Sale("other description", true);

    @Before
    public void setUp() throws Exception {
        ownerPlace1.setSales(Lists.newArrayList(sale1, sale2));
        ownerPlace2.setSales(Lists.newArrayList(sale2));
    }

    @Test
    public void testGetAll() throws Exception {
        ElasticPlaceDao elasticPlaceDao = new ElasticPlaceDao();

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
        ElasticPlaceDao elasticPlaceDao = new ElasticPlaceDao();

        elasticPlaceDao.save(ownerPlace1);
        elasticPlaceDao.save(ownerPlace2);
        elasticPlaceDao.save(ownerPlace3);
        elasticPlaceDao.save(ownerPlace4);

        timeout();

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

    private void timeout(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}