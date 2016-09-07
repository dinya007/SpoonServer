package ru.mipt.restaurant.server;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import ru.mipt.restaurant.server.dao.impl.InMemoryDiscountDao;
import ru.mipt.restaurant.server.dao.impl.InMemoryOwnerDao;

@Configuration
@EnableAutoConfiguration
@ImportResource("classpath:application-config.xml")
@ComponentScan(basePackages = {"ru.mipt.restaurant.server"}, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {InMemoryApplication.class, InMemoryDiscountDao.class, InMemoryOwnerDao.class})})
public class Application {

//    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
//    }

}
