package ru.mipt.restaurant.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import ru.mipt.restaurant.server.dao.impl.CassandraOwnerDao;
import ru.mipt.restaurant.server.dao.impl.CassandraPlaceDao;

@Configuration
@EnableAutoConfiguration(exclude = {CassandraDataAutoConfiguration.class, SecurityAutoConfiguration.class})
@ComponentScan(basePackages = {"ru.mipt.restaurant.server"}, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {Application.class, CassandraPlaceDao.class, CassandraOwnerDao.class})})
public class InMemoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(InMemoryApplication.class, args);
    }

//    @Bean
//    public WebSecurityConfigurerAdapter webSecurityConfigurerAdapter() {
//        return new WebSecurityRouterConfig();
//    }

}
