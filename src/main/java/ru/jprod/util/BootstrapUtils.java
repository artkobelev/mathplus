package ru.jprod.util;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BootstrapUtils
{
    private static final String BASE_DIR_PROPERTY = "base.dir";
    private static final String EXTERNAL_CONFIG_DIR_PROPERTY = "ext.prop.dir";
    private static final String LOGGING_DIR_PROPERTY = "logs.dir";
    private static final String LOGBACK_CONFIG_NAME = "logback.xml";

    private static final Logger LOGGER = LoggerFactory.getLogger(BootstrapUtils.class);

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
            path = Paths.get("").toString();
            System.setProperty(BASE_DIR_PROPERTY, path);
            LOGGER.warn(String.format(
                    "'%s' property is not set. Using current directory as base dir", BASE_DIR_PROPERTY));
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
            Path confPath = Paths.get(confDir);
            System.setProperty("logging.config", confPath.resolve(LOGBACK_CONFIG_NAME).toString());
        }
    }
}
