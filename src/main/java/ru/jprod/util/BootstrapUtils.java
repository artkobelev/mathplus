package ru.jprod.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.jprod.util.exceptions.MathplusException;

/**
 * Класс описывает действия, выполняемые до старта spring приложения
 *
 * @author akobelev
 * @since 23.05.2019
 */
public class BootstrapUtils
{
    private static final String BASE_DIR_PROPERTY = "base.dir";
    private static final String EXTERNAL_CONFIG_DIR_PROPERTY = "ext.prop.dir";
    private static final String LOGBACK_CONFIG_NAME = "logback.xml";
    private static final Logger LOGGER = LoggerFactory.getLogger(BootstrapUtils.class);
    private static final String LOGGING_DIR_PROPERTY = "logs.dir";

    /**
     * Установить следующие настройки:
     * <ul>
     * <li>base.dir - если не задан, равен текущей директории</li>
     * <li>ext.prop.dir - если не задан, используются настройки по умолчанию</li>
     * <li>logs.dir - директория с логами риложения (base.dir/logs)</li>
     * <ul/>
     * <li>Иерархия директорий:
     * <pre>
     *   base.dir
     *     - logs
     *     - data/h2/dump.mv.db
     *   ext.prop.dir
     *     - logback.xml
     *     - config.properties
     * </pre>
     * <li/>
     */
    public static void initProperties()
    {
        Path baseDir = initBaseDir();
        initConfig();
        System.setProperty(LOGGING_DIR_PROPERTY, (baseDir.resolve("logs").toString()));
    }

    /**
     * Установить свойство base.dir
     *
     * @return путь в директории
     */
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

    /**
     * Установить свойство ext.prop.dir
     */
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
