package ru.jprod.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.jprod.util.exceptions.MathplusException;

public class BootstrapUtils
{
    private static final String BASE_DIR_PROPERTY = "base.dir";
    private static final String EXTERNAL_CONFIG_DIR_PROPERTY = "ext.prop.dir";
    private static final String LOGBACK_CONFIG_NAME = "logback.xml";
    private static final Logger LOGGER = LoggerFactory.getLogger(BootstrapUtils.class);
    private static final String LOGGING_DIR_PROPERTY = "logs.dir";

    public static void initProperties()
    {
        Path baseDir = initBaseDir();
        initConfig();
        System.setProperty(LOGGING_DIR_PROPERTY, (baseDir.resolve("logs").toString()));
    }

    private static Path initBaseDir()
    {
        String path = System.getProperty(BASE_DIR_PROPERTY);
        Path baseDir;
        if (null == path)
        {
            throw new MathplusException("'base.dir' property is not set");
        }
        baseDir = Paths.get(path);

        return baseDir;
    }

    private static void initConfig()
    {
        String confDir = System.getProperty(EXTERNAL_CONFIG_DIR_PROPERTY);
        if (null == confDir)
        {
            LOGGER.warn(String.format("'%s' property is not set. Using default configuration",
                    EXTERNAL_CONFIG_DIR_PROPERTY));
        }
        else
        {
            // Configure logback.xml
            Path confPath = Paths.get(confDir);
            if (Files.exists(confPath.resolve(LOGBACK_CONFIG_NAME)))
            {
                System.setProperty("logging.config", confPath.resolve(LOGBACK_CONFIG_NAME).toString());
            }
            else
            {
                LOGGER.warn("logging configuration not found. Using default");
            }
        }
    }
}
