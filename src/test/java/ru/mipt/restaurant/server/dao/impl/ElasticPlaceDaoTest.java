package ru.mipt.restaurant.server.dao.impl;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.mipt.restaurant.server.domain.Location;
import ru.mipt.restaurant.server.domain.Place;
import ru.mipt.restaurant.server.domain.Sale;

import java.util.List;

public class ElasticPlaceDaoTest extends ElasticDaoTest {

    private Location location1 = new Location(55.754695, 37.621527);
    private Place place1 = new Place(location1, "ReStore", "Address 1", "Скидки на планшеты и ноутбуки", "e1@mail.com");

    private Location location2 = new Location(55.750763, 37.596108);
    private Place place2 = new Place(location2, "Starbucks", "Address 2","Кофе по цене чая", "e2@mail.com");

    private Location location3 = new Location(55.756852, 37.614048);
    private Place place3 = new Place(location3, "Vertu", "Address 3","Шиш вам, а не скидки", "toma-vesta@mail.ru");

    private Location location4 = new Location(0.0, 0.0);
    private Place place4 = new Place(location4, "Чебуреки", "Address 4","Чебуречная в РТС", "toma-vesta@mail.ru");

    private Sale sale1 = new Sale("some description", true);
    private Sale sale2 = new Sale("other description", true);

    @Before
    public void setUp() throws Exception {
        place1.setSales(Lists.newArrayList(sale1, sale2));
        place2.setSales(Lists.newArrayList(sale2));
    }

    @Test
    public void testGetAll() throws Exception {
        ElasticPlaceDao elasticPlaceDao = new ElasticPlaceDao();

        elasticPlaceDao.save(place1);
        elasticPlaceDao.save(place2);

        Thread.sleep(1000);

        List<Place> result = elasticPlaceDao.getAll();
        Assert.assertEquals(2, result.size());

        Assert.assertTrue(result.contains(place1));
        Assert.assertTrue(result.contains(place2));
    }

    @Test
    public void testGetAllInArea() throws Exception {
        ElasticPlaceDao elasticPlaceDao = new ElasticPlaceDao();

        elasticPlaceDao.save(place1);
        elasticPlaceDao.save(place2);
        elasticPlaceDao.save(place3);
        elasticPlaceDao.save(place4);
        Thread.sleep(10000);

        List<Place> result = elasticPlaceDao.getAllInArea(new Location(56.0, 37.0), new Location(55.0, 38.0));

        Assert.assertEquals(3, result.size());
        Assert.assertTrue(result.contains(place1));
        Assert.assertTrue(result.contains(place2));
        Assert.assertTrue(result.contains(place3));
    }

    @Test
    public void testUpdate() throws Exception {
        ElasticPlaceDao elasticPlaceDao = new ElasticPlaceDao();
        elasticPlaceDao.save(place1);
        Thread.sleep(1000);

        String address = "new address";
        place1.setAddress(address);
        elasticPlaceDao.save(place1);
        Thread.sleep(1000);

        List<Place> places = elasticPlaceDao.getAll();

        Assert.assertEquals(1, places.size());
        Assert.assertEquals(place1, places.get(0));
        Assert.assertEquals(address, places.get(0).getAddress());
    }

    @Test
    public void testMultipleUpdates() throws Exception {
        ElasticPlaceDao elasticPlaceDao = new ElasticPlaceDao();
        elasticPlaceDao.save(place1);
        elasticPlaceDao.save(place2);
        elasticPlaceDao.save(place3);
        elasticPlaceDao.save(place4);
        Thread.sleep(1000);

        String address1 = "new address 1";
        String address4 = "new address 4";
        place1.setAddress(address1);
        place4.setAddress(address4);
        elasticPlaceDao.save(place1);
        elasticPlaceDao.save(place2);
        elasticPlaceDao.save(place3);
        elasticPlaceDao.save(place4);
        Thread.sleep(1000);

        List<Place> places = elasticPlaceDao.getAll();

        Assert.assertEquals(4, places.size());
        Assert.assertEquals(address1, places.get(places.indexOf(place1)).getAddress());
        Assert.assertEquals(place2.getAddress(), places.get(places.indexOf(place2)).getAddress());
        Assert.assertEquals(place3.getAddress(), places.get(places.indexOf(place3)).getAddress());
        Assert.assertEquals(address4, places.get(places.indexOf(place4)).getAddress());
    }

}