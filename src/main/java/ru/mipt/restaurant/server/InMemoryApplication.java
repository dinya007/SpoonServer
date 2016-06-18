package ru.mipt.restaurant.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import ru.mipt.restaurant.server.dao.impl.CassandraDiscountDao;

@Configuration
@EnableAutoConfiguration(exclude = {CassandraDataAutoConfiguration.class})
@ComponentScan(basePackages = {"ru.mipt.restaurant.server"}, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {CassandraDiscountDao.class, Application.class})})
public class InMemoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(InMemoryApplication.class, args);
    }


}
