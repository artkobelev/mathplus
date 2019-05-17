package ru.jprod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import ru.jprod.config.ConfigProperties;
import ru.jprod.config.MathProperties;
import ru.jprod.util.BootstrapUtils;

@SpringBootApplication
@EnableConfigurationProperties({ ConfigProperties.class, MathProperties.class })
@PropertySource("classpath:/config-default.properties")
@PropertySource(value = "file:${ext.prop.dir}/config.properties", ignoreResourceNotFound = true)
public class Application
{
    static
    {
        BootstrapUtils.initProperties();
    }

    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }
}