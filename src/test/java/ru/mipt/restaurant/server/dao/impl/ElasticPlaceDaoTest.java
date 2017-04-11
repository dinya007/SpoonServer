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

    Location location1 = new Location(55.754695, 37.621527);
    Place place1 = new Place(location1, "ReStore", "Скидки на планшеты и ноутбуки", "e1@mail.com");

    Location location2 = new Location(55.750763, 37.596108);
    Place place2 = new Place(location2, "Starbucks", "Кофе по цене чая", "e2@mail.com");

    Location location3 = new Location(55.756852, 37.614048);
    Place place3 = new Place(location3, "Vertu", "Шиш вам, а не скидки", "toma-vesta@mail.ru");

    Location location4 = new Location(0.0, 0.0);
    Place place4 = new Place(location4, "Чебуреки", "Чебуречная в РТС", "toma-vesta@mail.ru");

    Sale sale1 = new Sale(10, "some description");
    Sale sale2 = new Sale(20, "other description");

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

}