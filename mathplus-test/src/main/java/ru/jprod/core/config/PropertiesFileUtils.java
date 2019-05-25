package ru.jprod.core.config;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Утилитарные методы, для загрузки конфигурации тестирующей системы из файла
 *
 * @author artem
 * @since 25.05.19
 */
public final class PropertiesFileUtils
{
    /**
     * Сообщение о том, что в метод не передано имя файла конфигурации. (передан null)
     */
    private static final String NEED_FILE_NAME = "Необходимо, чтобы имя файла конфигурации было не null.";

    private PropertiesFileUtils()
    {

    }

    /**
     * Загрузить конфигурацию из файла конфигурации
     *
     * @param fileName имя файла конфигурации
     * @return объект класса Properties содержащие пары ключ/значение взятые из файла конфигурации
     */
    public static Properties loadPropertiesFromFile(String fileName) throws RuntimeException
    {
        checkNotNull(fileName, NEED_FILE_NAME);
        try (FileInputStream fis = new FileInputStream(Paths.get(fileName).toAbsolutePath().toFile()))
        {
            Properties properties = new Properties();
            properties.load(fis);
            return properties;
        }
        catch (IOException e)
        {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
