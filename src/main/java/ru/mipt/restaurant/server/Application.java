package ru.mipt.restaurant.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import ru.mipt.restaurant.server.dao.impl.InMemoryDiscountDao;

@Configuration
@EnableAutoConfiguration
@ImportResource("classpath:application-config.xml")
@ComponentScan(basePackages = {"ru.mipt.restaurant.server"}, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {InMemoryDiscountDao.class, InMemoryApplication.class})})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
