package ru.akob.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExtConfigProperties
{
    @Value("${ext.prop1}")
    private String prop1;
    @Value("${ext.prop2}")
    private String prop2;
    @Value("${ext.prop3}")
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