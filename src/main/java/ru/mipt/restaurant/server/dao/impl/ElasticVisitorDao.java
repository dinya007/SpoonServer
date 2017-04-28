package ru.mipt.restaurant.server.dao.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.mipt.restaurant.server.dao.VisitorDao;
import ru.mipt.restaurant.server.domain.Visitor;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ElasticVisitorDao implements VisitorDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticPlaceDao.class);
    private final ObjectMapper mapper = new ObjectMapper();

    private final TransportClient client;

    private GetRequestBuilder getByUid;

    @Autowired
    public ElasticVisitorDao(TransportClient client) {
        this.client = client;

    }

    @PostConstruct
    private void init() {
        getByUid = this.client.prepareGet(Index.VISITORS.getName(), Type.VISITOR.getName(), null);
    }

    @Override
    public Visitor get(String uid) {
        GetRequestBuilder getRequestBuilder = getByUid.setId(uid);
        LOGGER.debug("Request by uid: {}", getRequestBuilder);
        GetResponse response = getRequestBuilder.get();
        LOGGER.debug("Response: {}", response);
        String json = response.getSourceAsString();
        return (json == null) ? null : map(json, Visitor.class);
    }

    @Override
    public List<Visitor> getByPlaceId(String id) {
        SearchRequestBuilder searchRequestBuilder = getSearchRequestBuilder().setQuery(QueryBuilders.nestedQuery("places",
                QueryBuilders.boolQuery()
                        .must(QueryBuilders.matchQuery("places.id", id))));

        LOGGER.debug("Request by place id: {}", searchRequestBuilder.internalBuilder());
        SearchResponse response = searchRequestBuilder.get();
        LOGGER.debug("Response: {}", response);

        return mapResults(response.getHits().hits(), Visitor.class);
    }

    @Override
    public Visitor save(Visitor visitor) {
        try {
            IndexRequestBuilder index = client.prepareIndex(Index.VISITORS.getName(), Type.VISITOR.getName());

            IndexRequestBuilder indexRequestBuilder = index.setSource(mapper.writeValueAsString(visitor));
            indexRequestBuilder.setId(visitor.getUid());

            LOGGER.debug("Saving visitor: {}", indexRequestBuilder);
            IndexResponse response = indexRequestBuilder.get();
            LOGGER.debug("Response. {}", response);
            return visitor;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Visitor> getAll() {
        SearchRequestBuilder search = getSearchRequestBuilder();

        LOGGER.debug("Box request: {}", search.internalBuilder());
        SearchResponse searchResponse = search.get();
        LOGGER.debug("Box response: {}", searchResponse);
        return mapResults(searchResponse.getHits().hits(), Visitor.class);
    }

    private <T extends Visitor> List<T> mapResults(SearchHit[] results, Class<T> clazz) {
        List<T> result = new ArrayList<>();
        for (SearchHit searchHitField : results) {
            T object = map(searchHitField.getSourceAsString(), clazz);
            result.add(object);
        }
        return result;
    }

    private <T> T map(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private SearchRequestBuilder getSearchRequestBuilder() {
        return client.prepareSearch(Index.VISITORS.getName());
    }

}
