package ru.mipt.restaurant.server.dao.impl;

import org.junit.Assert;
import org.junit.Test;
import ru.mipt.restaurant.server.dao.GeocoderDao;
import ru.mipt.restaurant.server.domain.Location;

public class GoogleGeocoderDaoTest {

    @Test
    public void testGeocodeFromAddress() throws Exception {
        GeocoderDao googleGeocoder = new GoogleGeocoderDao();

        Location location = googleGeocoder.geocode("Москва Днепропетровская улица 39 корпус 1");

        Assert.assertEquals(new Location(55.6152987, 37.5902906), location);
    }

    @Test
    public void testGeocodeFromLocation() throws Exception {
        GeocoderDao googleGeocoder = new GoogleGeocoderDao();

        String address = googleGeocoder.geocode(new Location(55.6152987, 37.5902906));

        Assert.assertEquals("Днепропетровская ул., 39к1, Москва, Россия, 117570", address);
    }

}