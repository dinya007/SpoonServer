package ru.mipt.restaurant.server.dao.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.GeoBoundingBoxQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.mipt.restaurant.server.dao.PlaceDao;
import ru.mipt.restaurant.server.domain.Location;
import ru.mipt.restaurant.server.domain.OwnerPlace;
import ru.mipt.restaurant.server.domain.Place;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ElasticPlaceDao implements PlaceDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticPlaceDao.class);
    private final ObjectMapper mapper = new ObjectMapper();
    private final TransportClient client;
    private GeoBoundingBoxQueryBuilder boundingBoxFilter;
    private GetRequestBuilder getById;

    @Autowired
    public ElasticPlaceDao(TransportClient client) {
        this.client = client;
    }

    @PostConstruct
    private void init(){
        getById = this.client.prepareGet(Index.PLACES.getName(), Type.RESTAURANT.getName(), null);

        boundingBoxFilter = new GeoBoundingBoxQueryBuilder("location");
    }

    @Override
    public List<OwnerPlace> getAll() {
        SearchRequestBuilder search = getSearchRequestBuilder();

        LOGGER.debug("Box request: {}", search.internalBuilder());
        SearchResponse searchResponse = search.get();
        LOGGER.debug("Box response: {}", searchResponse);
        return mapResults(searchResponse.getHits().hits(), OwnerPlace.class);
    }

    @Override
    public List<OwnerPlace> getAllInArea(Location topLeft, Location bottomRight) {
        SearchRequestBuilder search = getSearchRequestBuilder();

        GeoBoundingBoxQueryBuilder queryBuilder = boundingBoxFilter
                .topLeft(topLeft.getLat(), topLeft.getLon())
                .bottomRight(bottomRight.getLat(), bottomRight.getLon());

        SearchRequestBuilder searchRequestBuilder = search.setQuery(queryBuilder);

        LOGGER.debug("Box request: {}", searchRequestBuilder.internalBuilder());
        SearchResponse searchResponse = searchRequestBuilder.get();
        LOGGER.debug("Box response: {}", searchResponse);

        return mapResults(searchResponse.getHits().hits(), OwnerPlace.class);
    }

    @Override
    public OwnerPlace save(OwnerPlace ownerPlace) {
        try {
            IndexRequestBuilder index = client.prepareIndex(Index.PLACES.getName(), Type.RESTAURANT.getName());

            IndexRequestBuilder indexRequestBuilder = index.setSource(mapper.writeValueAsString(ownerPlace));
            if (ownerPlace.getId() != null) {
                index.setId(ownerPlace.getId());
            }
            LOGGER.debug("Saving new ownerPlace. {}", ownerPlace);
            IndexResponse response = indexRequestBuilder.get();
            ownerPlace.setId(response.getId());
            LOGGER.debug("Response. {}", response);
            return ownerPlace;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public OwnerPlace get(String id) {
        GetRequestBuilder getRequestBuilder = getById.setId(id);
        LOGGER.debug("Request by id: {}", getRequestBuilder);
        GetResponse response = getRequestBuilder.get();
        LOGGER.debug("Response: {}", response);
        return mapPlace(response.getSourceAsString(), OwnerPlace.class);
    }

    @Override
    public String delete(String id) {
        DeleteRequestBuilder deleteRequestBuilder = client.prepareDelete(Index.PLACES.getName(), Type.RESTAURANT.getName(), id);
        LOGGER.debug("Delete request: {}", deleteRequestBuilder);
        DeleteResponse deleteResponse = deleteRequestBuilder.get();
        LOGGER.debug("Delete response: {}", deleteResponse);
        return id;
    }

    @Override
    public List<OwnerPlace> getAllByOwner(String login) {
        SearchRequestBuilder search = getSearchRequestBuilder();

        SearchRequestBuilder searchRequestBuilder = search.setQuery(QueryBuilders.termQuery("login", login));

        LOGGER.debug("Request by login: {}", searchRequestBuilder.internalBuilder());
        SearchResponse response = searchRequestBuilder.get();
        LOGGER.debug("Response: {}", response);

        return mapResults(response.getHits().hits(), OwnerPlace.class);
    }

    private <T extends Place> List<T> mapResults(SearchHit[] results, Class<T> clazz) {
        List<T> result = new ArrayList<>();
        for (SearchHit searchHitField : results) {
            T ownerPlace = mapPlace(searchHitField.getSourceAsString(), clazz);
            ownerPlace.setId(searchHitField.getId());
            result.add(ownerPlace);
        }
        return result;
    }

    private <T> T mapPlace(String jsonPlace, Class<T> clazz) {
        try {
            return mapper.readValue(jsonPlace, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private SearchRequestBuilder getSearchRequestBuilder() {
        return client.prepareSearch(Index.PLACES.getName());
    }

}
