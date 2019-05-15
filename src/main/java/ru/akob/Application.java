package ru.akob;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "file:${ext.prop.dir}/config.properties", ignoreResourceNotFound = false)
@PropertySource(value = "file:${ext.prop.dir}/extconfig.properties", ignoreResourceNotFound = false)
public class Application
{
    static
    {
        initProperties();
    }

    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }

    private static void initProperties()
    {
        String path = System.getProperty("base.dir");
        Path baseDir;
        if (null == path)
        {
            path = Paths.get("").toString();
            System.setProperty("base.dir", path);
            System.out.println("'base.dir' property is not set. Using current directory as base");
        }

        baseDir = Paths.get(path);
        if (!Files.isDirectory(baseDir))
        {
            throw new RuntimeException("base.dir = '" + path + "' is not a directory");
        }
        System.setProperty("logs.dir", (baseDir.resolve("logs").toString()));
        if (null == System.getProperty("ext.prop.dir"))
        {
            Path confDir = baseDir.resolve("conf");
            System.setProperty("ext.prop.dir", confDir.toString());
            System.setProperty("logging.config", confDir.resolve("logback.xml").toString());
        }
    }
}