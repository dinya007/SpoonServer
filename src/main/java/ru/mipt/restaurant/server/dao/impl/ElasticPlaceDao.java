package ru.mipt.restaurant.server.dao.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.GeoBoundingBoxQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.mipt.restaurant.server.dao.PlaceDao;
import ru.mipt.restaurant.server.domain.Location;
import ru.mipt.restaurant.server.domain.Owner;
import ru.mipt.restaurant.server.domain.Place;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

//@Profile({"testing", "production"})
@Repository
public class ElasticPlaceDao implements PlaceDao {

    private Logger logger = LoggerFactory.getLogger(ElasticPlaceDao.class);

    private IndexRequestBuilder index;
    private ObjectMapper mapper;
    private GeoBoundingBoxQueryBuilder boundingBoxFilter;
    private TransportClient client;
    private SearchRequestBuilder search;

    public ElasticPlaceDao() {
        Settings settings = Settings.builder()
                .put("cluster.name", "restaurant-server")
                .put("client.transport.sniff", true)
                .build();

        try {
            client = TransportClient.builder().settings(settings).build()
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        index = client.prepareIndex(Index.PLACES.getName(), Type.RESTAURANT.getName());

        mapper = new ObjectMapper();

        search = client.prepareSearch(Index.PLACES.getName());

        boundingBoxFilter = new GeoBoundingBoxQueryBuilder("location");
    }

    @Override
    public List<Place> getAll() {
        logger.debug("Box request: {}", search.internalBuilder());
        SearchResponse searchResponse = search.get();
        logger.debug("Box response: {}", searchResponse);
        return mapResults(searchResponse.getHits().hits());
    }

    @Override
    public List<Place> getAllInArea(Location topLeft, Location bottomRight) {
        GeoBoundingBoxQueryBuilder queryBuilder = boundingBoxFilter
                .topLeft(topLeft.getLat(), topLeft.getLon())
                .bottomRight(bottomRight.getLat(), bottomRight.getLon());

        SearchRequestBuilder searchRequestBuilder = search.setQuery(queryBuilder);

        logger.debug("Box request: {}", searchRequestBuilder.internalBuilder());
        SearchResponse searchResponse = searchRequestBuilder.get();
        logger.debug("Box response: {}", searchResponse);

        return mapResults(searchResponse.getHits().hits());
    }

    @Override
    public Place save(Place place) {
        try {
            IndexRequestBuilder indexRequestBuilder = index.setSource(mapper.writeValueAsString(place));
            if (place.getId() != null) {
                index.setId(place.getId());
            }
            logger.debug("Saving new place. {}", place);
            IndexResponse response = indexRequestBuilder.get();
            place.setId(response.getId());
            logger.debug("Response. {}", response);
            return place;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Place delete(Place place) {
        SearchRequestBuilder searchRequestBuilder = search.setQuery(QueryBuilders.boolQuery()
                .must(QueryBuilders.termQuery("location.lat", place.getLocation().getLat()))
                .must(QueryBuilders.termQuery("location.lon", place.getLocation().getLon()))
        );
        logger.debug("Request by coordinates: {}", searchRequestBuilder.internalBuilder());
        SearchResponse response = searchRequestBuilder.get();
        logger.debug("Response: {}", response);


        if (response.getHits().hits().length != 1)
            throw new RuntimeException("Search result hits not equal 1: " + response);


        DeleteRequestBuilder deleteRequestBuilder = client.prepareDelete(Index.PLACES.getName(), Type.RESTAURANT.getName(), response.getHits().hits()[0].getId());
        logger.debug("Delete request: {}", deleteRequestBuilder);
        DeleteResponse deleteResponse = deleteRequestBuilder.get();
        logger.debug("Delete response: {}", deleteResponse);

        return place;
    }

    @Override
    public List<Place> getAllByOwner(String email) {
        SearchRequestBuilder searchRequestBuilder = search.setQuery(QueryBuilders.termQuery("ownerEmail", email));

        logger.debug("Request by email: {}", searchRequestBuilder.internalBuilder());
        SearchResponse response = searchRequestBuilder.get();
        logger.debug("Response: {}", response);

        return mapResults(response.getHits().hits());
    }

    @Override
    public List<Place> getAllByOwner(Owner owner) {
        return getAllByOwner(owner.getEmail());
    }

    private List<Place> mapResults(SearchHit[] results) {
        List<Place> result = new ArrayList<>();
        for (SearchHit searchHitField : results) {
            try {
                Place place = mapper.readValue(searchHitField.getSourceAsString(), Place.class);
                place.setId(searchHitField.getId());
                result.add(place);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    @PreDestroy
    private void onDestroy() {
        client.close();
    }
}
