package ru.mipt.restaurant.server.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import ru.mipt.restaurant.server.ElasticTest;
import ru.mipt.restaurant.server.domain.OwnerPlace;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class PlaceControllerTest extends ElasticTest {

    private final ObjectMapper mapper = new ObjectMapper();


    @Before
    public void initES() throws Exception {
        initPlaces();

        ownerPlace1.setSales(Lists.newArrayList(sale1, sale2));
        ownerPlace2.setSales(Lists.newArrayList(sale2));
    }

    @Test
    public void testGetPlacesByCoordinates() throws Exception {
        String jsonResponse = mvc.perform(get("/places/56.0/37.0/55.0/38.0")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();


        CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, OwnerPlace.class);
        List<OwnerPlace> result = mapper.readValue(jsonResponse, collectionType);

        Assert.assertEquals(2, result.size());
        Assert.assertTrue(result.contains(ownerPlace1));
        Assert.assertTrue(result.contains(ownerPlace2));
        Assert.assertNull(result.get(0).getLogin());
        Assert.assertNull(result.get(1).getLogin());
    }

}