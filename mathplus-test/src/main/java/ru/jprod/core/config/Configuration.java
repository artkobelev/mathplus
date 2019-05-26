package ru.jprod.core.config;

import java.util.Properties;

/**
 * Класс загружает настройки тестовой системы из файла config.properties
 *
 * @author artem
 * @since 25.05.2019
 */
public final class Configuration
{
    public static final String APP_PORT_PROP = "app.port";
    private static final String APP_HOST_PROP = "app.host";
    private static final String ARITHMETIC_DELTA_PROP = "arithm.delta";

    private static final String CONFIG_PROP_NAME = "config";
    private static Configuration instance;
    private Properties properties;

    private Configuration()
    {
        properties = getPropertyFromFile();
    }

    /**
     * Получение ссылки на экземпляр класса конфигурации
     *
     * @return возвращает ссылку на единственный экземпляр класса
     */
    public static Configuration get()
    {
        if (instance == null)
        {
            instance = new Configuration();
        }
        return instance;
    }

    /**
     * @return host тестируемого приложения
     */
    public String getAppHost()
    {
        return properties.getProperty(APP_HOST_PROP, "localhost");
    }

    /**
     * @return http порт тестируемого приложения
     */
    public int getAppPort()
    {
        return Integer.valueOf(properties.getProperty(APP_PORT_PROP, "8080"));
    }

    /**
     * @return погрешность вычисления
     */
    public double getDelta()
    {
        return Double.valueOf(properties.getProperty(ARITHMETIC_DELTA_PROP, "1e-5"));
    }

    /**
     * Получить свойства из файла конфигурации. Если файл конфигурации отсутствует, то метод вернет пустой набор свойств
     *
     * @return метод возвращает свойства, полученные из файла конфигурации. Если файл конфигурации отсутствует,
     * то будет возвращен пустой набор свойств
     */
    private Properties getPropertyFromFile()
    {
        String configFileName = System.getProperties().getProperty(CONFIG_PROP_NAME,
                "config.properties");
        return PropertiesFileUtils.loadPropertiesFromFile(configFileName);
    }
}
