package ru.mipt.restaurant.server;

import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mipt.restaurant.server.dao.impl.ElasticPlaceDao;
import ru.mipt.restaurant.server.domain.Location;
import ru.mipt.restaurant.server.domain.OwnerPlace;
import ru.mipt.restaurant.server.domain.Sale;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class ElasticTest extends SpringTest {

    private static final String TEST_ES_DATA = "test_es_data";
    private static Node esNode;

    @Autowired
    protected ElasticPlaceDao elasticPlaceDao;

    protected Location location1 = new Location(55.754695, 37.621527);
    protected OwnerPlace ownerPlace1 = new OwnerPlace(location1, "ReStore", "Address 1", "Скидки на планшеты и ноутбуки", "e1@mail.com", "login1");

    protected Location location2 = new Location(55.750763, 37.596108);
    protected OwnerPlace ownerPlace2 = new OwnerPlace(location2, "Starbucks", "Address 2", "Кофе по цене чая", "e2@mail.com", "login2");

    protected Location location3 = new Location(55.756852, 37.614048);
    protected OwnerPlace ownerPlace3 = new OwnerPlace(location3, "Vertu", "Address 3", "Шиш вам, а не скидки", "toma-vesta@mail.ru", "toma");

    protected Location location4 = new Location(0.0, 0.0);
    protected OwnerPlace ownerPlace4 = new OwnerPlace(location4, "Чебуреки", "Address 4", "Чебуречная в РТС", "toma-vesta@mail.ru", "toma");

    protected Sale sale1 = new Sale("some description", true);
    protected Sale sale2 = new Sale("other description", true);

    @BeforeClass
    public static void initElasticsearch() throws Exception {
        esNode = NodeBuilder.nodeBuilder().clusterName("restaurant-server").settings(
                Settings.builder().put("node.local", "false",
                        "network.host", "localhost",
                        "http.enabled", "true",
                        "path.home", "test_es_home",
                        "path.data", TEST_ES_DATA))
                .node();
        esNode.start();
    }

    @AfterClass
    public static void stopElasticsearch() throws Exception {
        esNode.close();
        FileUtils.deleteDirectory(new File(TEST_ES_DATA));
    }

    private static void printOutput(Process process) throws IOException {
        BufferedReader output = new BufferedReader(new
                InputStreamReader(process.getErrorStream()));
        String s;
        while ((s = output.readLine()) != null) {
            System.out.println("STDERR: " + s);
        }

        output = new BufferedReader(new
                InputStreamReader(process.getInputStream()));
        while ((s = output.readLine()) != null) {
            System.out.println("STDOUT: " + s);
        }
    }

    @Before
    public void setUp() throws Exception {
        ownerPlace1.setSales(Lists.newArrayList(sale1, sale2));
        ownerPlace2.setSales(Lists.newArrayList(sale2));
    }

    @Before
    public void initElasticsearchData() throws Exception {
        Process process = Runtime.getRuntime().exec("/Users/denis/Documents/Java/Idea_Projects/RestaurantServer/src/main/resources/scripts/es/create-all-indexes.sh");
        process.waitFor();
        printOutput(process);
    }

    @After
    public void stopElasticsearchData() throws Exception {
        Process process = Runtime.getRuntime().exec("/Users/denis/Documents/Java/Idea_Projects/RestaurantServer/src/main/resources/scripts/es/delete-all-indexes.sh");
        process.waitFor();
        printOutput(process);
    }

    protected void initPlaces() {
        elasticPlaceDao.save(ownerPlace1);
        elasticPlaceDao.save(ownerPlace2);
        elasticPlaceDao.save(ownerPlace3);
        elasticPlaceDao.save(ownerPlace4);

        timeout();
    }

    protected void timeout() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
