package ru.jprod.core;

import org.apache.http.Consts;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.EncoderConfig;

import ru.jprod.cleaner.Cleaner;
import ru.jprod.core.config.Configuration;

/**
 * Базовая настройка перед запуском тестов
 *
 * @author artem
 * @since 25.05.19
 */
public abstract class AbstractTestCase
{
    private static final String baseURI;
    private static final Cleaner cleaner = Cleaner.get();
    private static final Configuration config = Configuration.get();

    static
    {
        baseURI = "http://" + config.getAppHost() + ":" + config.getAppPort();
        RestAssured.baseURI = baseURI;
        Awaitility.setDefaultPollInterval(Duration.ONE_SECOND);
        RestAssured.config = RestAssured.config()
                .encoderConfig(new EncoderConfig(Consts.UTF_8.toString(), Consts.UTF_8.toString()));
    }

    /**
     * Действия выполняемые после завершения тестового класса.
     */
    @AfterClass
    public static void afterClass()
    {
        cleaner.clean(Cleaner.MODE.CLASS);
    }

    /**
     * Действия выполняемые перед выполнением тестового класса.
     */
    @BeforeClass
    public static void beforeClass()
    {
        cleaner.setMode(Cleaner.MODE.CLASS);
    }

    /**
     * Действия выполняемые после завершения тестового метода.
     */
    @After
    public void after()
    {
        cleaner.clean(Cleaner.MODE.TEST);
    }

    /**
     * Действия выполняемые перед выполнением тестового метода.
     */
    @Before
    public void before()
    {
        cleaner.setMode(Cleaner.MODE.TEST);
    }
}
