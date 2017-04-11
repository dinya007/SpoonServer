package ru.mipt.restaurant.server.dao.impl;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class ElasticDaoTest {

    @BeforeClass
    public static void initElasticsearch() throws Exception {
        Process process = Runtime.getRuntime().exec("/Users/denis/Documents/Java/Installations/elasticsearch-2.4.4/bin/elasticsearch -d");
        System.out.printf("Starting elasticsearch...\n");
        Thread.sleep(20000L);
        printOutput(process);
    }

    @Before
    public void initElasticsearchData() throws Exception {
        Process process = Runtime.getRuntime().exec("/Users/denis/Documents/Java/Idea_Projects/RestaurantServer/src/main/resources/scripts/es/create-place-index.sh");
        process.waitFor();
        printOutput(process);
        Thread.sleep(5000L);
    }

    @After
    public void stopElasticsearchData() throws Exception {
        Process process = Runtime.getRuntime().exec("/Users/denis/Documents/Java/Idea_Projects/RestaurantServer/src/main/resources/scripts/es/delete-place-index.sh");
        process.waitFor();
        printOutput(process);
        Thread.sleep(2000L);
    }

    @AfterClass
    public static void stopElasticsearch() throws Exception {
        System.out.printf("Stopping elasticsearch...");
        Process process = Runtime.getRuntime().exec("pkill -f elasticsearch");
        printOutput(process);
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
}
