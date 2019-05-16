package ru.jprod.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfiguration
{
    @Bean
    public DBConfigProperties dbConfigProperties()
    {
        return new DBConfigProperties();
    }
}
