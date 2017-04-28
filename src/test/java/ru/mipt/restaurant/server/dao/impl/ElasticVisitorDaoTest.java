package ru.mipt.restaurant.server.dao.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mipt.restaurant.server.ElasticTest;
import ru.mipt.restaurant.server.dao.VisitorDao;
import ru.mipt.restaurant.server.domain.Location;
import ru.mipt.restaurant.server.domain.Visitor;
import ru.mipt.restaurant.server.domain.VisitorPlace;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ElasticVisitorDaoTest extends ElasticTest {

    @Autowired
    private VisitorDao visitorDao;

    private VisitorPlace visitorPlace1 = VisitorPlace.builder()
            .address("Адрес")
            .description("Описание")
            .id(UUID.randomUUID().toString())
            .location(Location.builder().lat(0.0).lon(1.0).build())
            .ownerEmail("e@mail.com")
            .phone("+71234567890")
            .site("www.site.com")
            .visitorsAmount(4)
            .visitTime(new Date(1_000_000L))
            .startTime(new Date(1_000L))
            .locationName("Кафе")
            .build();

    private VisitorPlace visitorPlace2 = VisitorPlace.builder()
            .address("Адрес")
            .description("Описание")
            .id(UUID.randomUUID().toString())
            .build();

    private Visitor visitor = Visitor.builder()
            .name("Тома")
            .phoneNumber("+70123456789")
            .uid(UUID.randomUUID().toString())
            .place(visitorPlace1)
            .place(visitorPlace2)
            .build();

    @Test
    public void getByPlaceId() throws Exception {
        visitorDao.save(visitor);

        timeout();

        List<Visitor> result = visitorDao.getByPlaceId(visitor.getPlaces().get(0).getId());

        Assert.assertEquals(1, result.size());
        assertVisitor(visitor, result.get(0));
        assertVisitorPlace(visitorPlace1, result.get(0).getPlaces().get(0));
    }

    @Test
    public void testSaveAndGet() throws Exception {
        visitorDao.save(visitor);

        timeout();

        Visitor result = visitorDao.get(visitor.getUid());

        VisitorPlace visitorPlace1 = visitor.getPlaces().get(0);
        VisitorPlace visitorPlace2 = visitor.getPlaces().get(1);
        VisitorPlace resultPlace1 = result.getPlaces().get(0);
        VisitorPlace resultPlace2 = result.getPlaces().get(1);
        Assert.assertEquals(2, visitor.getPlaces().size());
        Assert.assertEquals(2, result.getPlaces().size());

        assertVisitor(visitor, result);
        assertVisitorPlace(visitorPlace1, resultPlace1);
        assertVisitorPlace(visitorPlace2, resultPlace2);
    }

    @Test
    public void getAll() throws Exception {
        visitorDao.save(visitor);

        timeout();

        List<Visitor> result = visitorDao.getAll();

        Visitor visitorResult = result.get(0);
        assertVisitor(visitor, visitorResult);
        assertVisitorPlace(visitorPlace1, visitorResult.getPlaces().get(0));
        assertVisitorPlace(visitorPlace2, visitorResult.getPlaces().get(1));
    }

    @Test
    public void testGetByUidWhenNotExist() throws Exception {
        Assert.assertNull(visitorDao.get("uid"));
    }

    private void assertVisitorPlace(VisitorPlace place1, VisitorPlace place2) {
        Assert.assertEquals(place1.getStartTime(), place2.getStartTime());
        Assert.assertEquals(place1.getVisitTime(), place2.getVisitTime());
        Assert.assertEquals(place1.getVisitorsAmount(), place2.getVisitorsAmount());
        Assert.assertEquals(place1.getAddress(), place2.getAddress());
        Assert.assertEquals(place1.getDescription(), place2.getDescription());
        Assert.assertEquals(place1.getId(), place2.getId());
        Assert.assertEquals(place1.getLocation(), place2.getLocation());
        Assert.assertEquals(place1.getLocationName(), place2.getLocationName());
        Assert.assertEquals(place1.getOwnerEmail(), place2.getOwnerEmail());
        Assert.assertEquals(place1.getPhone(), place2.getPhone());
        Assert.assertEquals(place1.getSite(), place2.getSite());
    }

    private void assertVisitor(Visitor visitor1, Visitor visitor2) {
        Assert.assertEquals(visitor1.getUid(), visitor2.getUid());
        Assert.assertEquals(visitor1.getName(), visitor2.getName());
        Assert.assertEquals(visitor1.getPhoneNumber(), visitor2.getPhoneNumber());
    }

}