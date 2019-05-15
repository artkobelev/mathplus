package ru.akob.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
public class ConfigProperties
{
    @Value("${app.prop1}")
    private String prop1;
    @Value("${app.prop2}")
    private String prop2;
    @Value("${app.prop3}")
    private String prop3;

    public String getProp1()
    {
        return prop1;
    }

    public String getProp2()
    {
        return prop2;
    }

    public String getProp3()
    {
        return prop3;
    }
}