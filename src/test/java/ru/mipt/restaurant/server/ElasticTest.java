package ru.mipt.restaurant.server;

import org.apache.commons.io.FileUtils;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class ElasticTest {

    private static final String TEST_ES_DATA = "test_es_data";
    private static Node esNode;


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
    public void initElasticsearchData() throws Exception {
        Process process = Runtime.getRuntime().exec("/Users/denis/Documents/Java/Idea_Projects/RestaurantServer/src/main/resources/scripts/es/create-place-index.sh");
        process.waitFor();
        printOutput(process);
    }

    @After
    public void stopElasticsearchData() throws Exception {
        Process process = Runtime.getRuntime().exec("/Users/denis/Documents/Java/Idea_Projects/RestaurantServer/src/main/resources/scripts/es/delete-place-index.sh");
        process.waitFor();
        printOutput(process);
    }

}
